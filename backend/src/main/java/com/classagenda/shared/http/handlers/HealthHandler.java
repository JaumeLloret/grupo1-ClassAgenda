package com.classagenda.shared.http.handlers;

import com.classagenda.shared.http.JsonResponses;
import com.classagenda.shared.http.ResponseContract;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public final class HealthHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Obtenemos el JSON estructurado y lo enviamos con código 200
        String jsonBody = ResponseContract.healthOkJson();
        JsonResponses.sendJson(exchange, 200, jsonBody);
    }
}