package de.jkrech.tutorial.todo;

import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ResourceLoader resourceLoader;

    protected void setup() {
        RestAssured.baseURI = "http://localhost/";
        RestAssured.port = getPort();
    }

    protected int getPort() {
        return port;
    }

    protected String readResource(final String path) {
        try {
            final Resource resource = resourceLoader.getResource("classpath:" + path);
            return FileUtils.readFileToString(resource.getFile(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
