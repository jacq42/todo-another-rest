[![PITest](https://github.com/jacq42/todo-another-rest/actions/workflows/testQuality.yml/badge.svg)](https://github.com/jacq42/todo-another-rest/actions/workflows/testQuality.yml)

# TODO another REST

## Purpose

This is a sample spring boot application to demonstrate the implementation of:
- REST Controller
- OpenAPI documentation
- Hibernate persistence layer
- Jenkins pipeline with SonarQube integration

The application lists existing TODOs and can create new items.

## API first approach

Created the [OpenAPI specification](src/main/resources/api-specs/todo-spec.yaml) first and generated the endpoints.
Added [APIIntegrationTest](src/test/java/de/jkrech/tutorial/todo/ports/todo/rest/TodoResourceApiIntegrationTest.java) with OpenApiValidationFilter to validate the implementation against the spec.

## Generated API Documentation approach

Start the application and access the OpenAPI documentation under:
- [Swagger UI](http://localhost:8080/swagger-ui/index.html)
- Api Doc as [Json](http://localhost:8080/v3/api-docs) or [YAML](http://localhost:8080/v3/api-docs.yaml)

## Persistence

Uses Hibernate and H2 in memory database

Connect to H2 console: http://localhost:8080/h2-console

Use JBDC URL: `jdbc:h2:mem:testdb` and user `sa` with empty password to connect to current database.

## Requirements

For testing the [Jenkins](https://www.jenkins.io/doc/book/installing/) and [SonarQube](https://www.sonarsource.com/de/products/sonarqube/) integration you need to install locally:

### Jenkins on Kubernetes

Create namespace:
```shell
kubectl create namespace devops-tools
```

Apply the service account:
```shell
cd ci
kubectl apply -f jenkins-01-serviceAccount.yml
```

Get worker node hostname and replace "worker-node01" in [jenkins-02-volume.yml](ci/jenkins-02-volume.yml):
```shell
kubectl get nodes
```

Apply the volume:
```shell
kubectl create -f jenkins-02-volume.yml
```

Apply the deployment:
```shell
kubectl apply -f jenkins-03-deployment.yml
```

Check the deployment status and deployment details:
```shell
kubectl get deployments -n devops-tools
kubectl describe deployments --namespace=devops-tools
```

Get IP of the node and access jenkins: http://<node-ip>:32000
```shell
kubectl get nodes -o wide
```

You need to unlock the jenkins with password, that can be found in the logs by doing the following:

Get pod name and get logs:
```shell
kubectl get pods --namespace=devops-tools
kubectl logs jenkins-5874c666f4-wcz27  --namespace=devops-tools
```
There is a message like "Please use the following password to proceed to installation"

After that you can install the suggested plugins and create the first admin user.

### SonarQube on Kubernetes

Apply the service:
```shell
kubectl apply -f sonarqube-service.yml
```

Get IP of the node and access sonarqube: http://<node-ip>:30900
```shell
kubectl get nodes -o wide
```

Login with admin/admin and change the password.

Create and use TOKEN for SonarQube in Jenkins:
1. Login to SonarQube as admin
2. Go to Administration > Security > Users > Tokens > Update tokens > Generate Token
3. Login to Jenkins
4. Go to Manage Jenkins > Manage Credentials > (global) > Add Credentials with ID "sonarqube-token" (type secret text)

## TODO

- [x] add domain objects
- [x] add service layer
- [x] add endpoint to create new TODOs
- [x] add endpoint to list all TODOs
- [ ] add OpenAPI documentation
- [x] add persistence with Hibernate and H2
- [x] add Jenkins pipeline with SonarQube integration