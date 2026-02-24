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

class ExampleIntroEndpointIT {

    // Cliente HTTP estático y reutilizable para hacer peticiones
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @Test
    void getExampleIntroReturnsJsonAnd200() throws Exception {
        // Forzamos el puerto 0 para que asigne uno dinámico libre
        System.setProperty("CLASSAGENDA_PORT", "0");
        HttpServerBootstrap bootstrap = new HttpServerBootstrap();

        // Arrancamos el servidor
        HttpServer httpServer = bootstrap.startAndReturnServer();
        int realPort = httpServer.getAddress().getPort();

        try {
            // Creamos la petición GET y la enviamos
            URI endpointUri = URI.create("http://localhost:" + realPort + "/example/intro");
            HttpRequest httpRequest = HttpRequest.newBuilder(endpointUri).GET().build();
            HttpResponse<String> httpResponse = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Verificamos el estado y que la respuesta contiene nuestro "ok"
            assertEquals(200, httpResponse.statusCode());
            assertTrue(httpResponse.body().contains("\"status\":\"ok\""));
        } finally {
            // Nos aseguramos de apagar el servidor incluso si el test falla
            httpServer.stop(0);
        }
    }
}