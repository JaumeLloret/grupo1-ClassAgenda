package com.classagenda.shared.http;

import com.classagenda.features.example.presentation.router.ExampleRouter;
import com.classagenda.shared.config.ServerConfig;
import com.classagenda.shared.http.handlers.HealthHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public final class JsonResponses {
    private JsonResponses() {}

    public static void sendJson(HttpExchange httpExchange, int statusCode, String jsonBody) throws IOException {
        // Transforma a bytes, añade cabecera JSON, responde y cierra
        byte[] responseBytes = jsonBody.getBytes(StandardCharsets.UTF_8);
        httpExchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
        httpExchange.sendResponseHeaders(statusCode, responseBytes.length);
        httpExchange.getResponseBody().write(responseBytes);
        httpExchange.close();
    }

    public static final class HttpServerBootstrap {
        public void start() throws Exception {
            HttpServer httpServer = startAndReturnServer();
            int serverPort = httpServer.getAddress().getPort();
            System.out.println("ClassAgenda API funcionando en http://localhost:" + serverPort);
        }

        public HttpServer startAndReturnServer() throws Exception {

            int configuredPort = ServerConfig.port();
            InetSocketAddress serverAddress = new InetSocketAddress(configuredPort);

            HttpServer httpServer = HttpServer.create(serverAddress, 0);

            httpServer.createContext("/health", new HealthHandler());
            ExampleRouter.registerRoutes(httpServer);

            httpServer.start();

            return httpServer;
        }
    }
}