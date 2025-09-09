# TODO another REST

Sample Spring boot application with a REST Controller 

* Lists TODOs and can delete one item by ID

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
- [ ] add endpoint to create new TODOs
- [ ] add endpoint to list all TODOs
- [ ] add OpenAPI documentation
- [x] add persistence with Hibernate and H2
- [x] add Jenkins pipeline with SonarQube integration