package com.classagenda.features.example.presentation;

import com.classagenda.shared.http.HttpServerBootstrap;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleEndpointIT {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @Test
    void getExampleIntoReturnsJsonAnd200() throws Exception {
        System.setProperty("CLASSAGENDA_PORT", "0");
        HttpServerBootstrap bootstrap = new HttpServerBootstrap();

        HttpServer httpServer = bootstrap.startAndReturnServer();
        int realPort = httpServer.getAddress().getPort();

        try {
            URI endpointUri = URI.create("http://localhost:" + realPort + "/example/intro");
            HttpRequest httpRequest = HttpRequest.newBuilder(endpointUri).GET().build();
            HttpResponse<String> httpResponse = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, httpResponse.statusCode());
            assertTrue(httpResponse.body().contains("\"status\":\"ok\""));

        } finally {
            httpServer.stop(0);
        }
    }
}
