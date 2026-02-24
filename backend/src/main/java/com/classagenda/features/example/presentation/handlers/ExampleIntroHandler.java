package com.classagenda.features.example.presentation.handlers;

import com.classagenda.shared.http.JsonResponses;
import com.classagenda.shared.http.ResponseContract;
import com.classagenda.shared.http.helpers.JsonEscaper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class ExampleIntroHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            // Evaluamos qué tipo de petición nos ha llegado (GET, POST, etc.)
            String httpMethod = httpExchange.getRequestMethod();
            switch (httpMethod) {
                case "GET" -> sendOk(httpExchange, "GET ok");
                case "POST" -> sendOk(httpExchange, "POST ok");
                case "PUT" -> sendOk(httpExchange, "PUT ok");
                case "PATCH" -> sendOk(httpExchange, "PATCH ok");
                case "DELETE" -> sendOk(httpExchange, "DELETE ok");
                default -> sendMethodNotAllowed(httpExchange); // Si envían algo raro
            }
        } catch (Exception exception) {
            // Si cualquier cosa falla, devolvemos un error 500 controlado
            sendServerError(httpExchange, exception.getMessage());
        }
    }

    private void sendOk(HttpExchange httpExchange, String message) throws IOException {
        // Leemos si el usuario nos ha enviado datos
        String receivedBody = readRequestBody(httpExchange);
        String dataJson = "{"
                + "\"endpoint\":\"example/intro\","
                + "\"method\":\"" + httpExchange.getRequestMethod() + "\","
                + "\"message\":\"" + JsonEscaper.escape(message) + "\","
                + "\"receivedBody\":" + toNullableJsonString(receivedBody)
                + "}";
        String responseJson = ResponseContract.okJson(dataJson);
        JsonResponses.sendJson(httpExchange, 200, responseJson);
    }

    private void sendMethodNotAllowed(HttpExchange httpExchange) throws IOException {
        // Indica los métodos permitidos y devuelve error 405
        httpExchange.getResponseHeaders().set("Allow", "GET, POST, PUT, PATCH, DELETE");
        String responseJson = ResponseContract.errorJson("Method not allowed", null);
        JsonResponses.sendJson(httpExchange, 405, responseJson);
    }

    private void sendServerError(HttpExchange httpExchange, String errorDetails) throws IOException {
        // Error interno del servidor (500)
        String responseJson = ResponseContract.errorJson("Internal server error", errorDetails);
        JsonResponses.sendJson(httpExchange, 500, responseJson);
    }

    private String readRequestBody(HttpExchange httpExchange) throws IOException {
        // Extrae el cuerpo del mensaje de la petición HTTP
        InputStream requestBodyStream = httpExchange.getRequestBody();
        if (requestBodyStream == null) return null;
        byte[] bodyBytes = requestBodyStream.readAllBytes();
        if (bodyBytes.length == 0) return null;
        return new String(bodyBytes, StandardCharsets.UTF_8);
    }

    private String toNullableJsonString(String rawValue) {
        if (rawValue == null) return "null";
        return "\"" + JsonEscaper.escape(rawValue) + "\"";
    }
}