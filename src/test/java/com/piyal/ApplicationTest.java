package com.piyal;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import jakarta.inject.Inject;
import java.util.Map;
import io.micronaut.http.HttpRequest;

@MicronautTest
@TestMethodOrder(OrderAnnotation.class)
class ApplicationTest {

    @Inject
    EmbeddedApplication<?> application;

	@Inject
	@Client("/")
	HttpClient httpClient;
	
    @Test
    @Order(1)
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    @Order(2)
    void testCreateBook() {
        HttpRequest<?> request = HttpRequest.POST("/api/v1/book/save",
            Map.of("name", "DevOps Handbook", "author", "Gene Kim", "price", 29.99, "totalPage", 480));
        String response = httpClient.toBlocking().retrieve(request);

        Assertions.assertTrue(response.contains("DevOps Handbook"));
    }

}
