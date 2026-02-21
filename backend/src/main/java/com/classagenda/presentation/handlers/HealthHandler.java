package com.classagenda.presentation.handlers;

import com.classagenda.presentation.utils.JsonResponses;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HealthHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Preparamos la respuesta JSON profesional
        String response = JsonResponses.success("Servidor operativo y saludable", "{\"database\": \"pending\"}");
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        // Configuramos cabeceras: indicamos que es JSON y el código 200 (OK)
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, bytes.length);

        // Enviamos el cuerpo de la respuesta
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}