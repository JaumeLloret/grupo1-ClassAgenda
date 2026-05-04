package com.classagenda.shared.http;

import com.classagenda.features.example.presentation.router.ExampleRouter;
import com.classagenda.shared.config.ServerConfig;
import com.classagenda.shared.http.handlers.HealthHandler;
// --- NUEVAS IMPORTACIONES PARA TASKS ---
import com.classagenda.features.task.presentation.handlers.TaskHandler;
import com.classagenda.features.task.domain.repository.TaskRepository; // Ajusta si el ejercicio te da otra
import com.classagenda.features.event.data.local.dao.EventDao; // Ajusta según el siguiente paso
import com.classagenda.shared.db.DbConnectionFactory; // Ajusta según el siguiente paso
import java.sql.Connection;
// ---------------------------------------
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public final class JsonResponses {
    private JsonResponses() {}

    public static void sendJson(HttpExchange httpExchange, int statusCode, String jsonBody) throws IOException {
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

            // --- CONFIGURACIÓN DE LA CAPA DE TAREAS (BLOQUE 7) ---
            // Nota: Estas líneas saldrán en rojo hasta que crees los archivos de DB
            DbConnectionFactory factory = new DbConnectionFactory();
            Connection sharedConnection = factory.open();

            EventDao taskDao = new EventDao(sharedConnection);
            // Aquí usamos el repositorio. Si el ejercicio te pide una implementación
            // específica como JdbcTaskRepository, cámbialo aquí:
            TaskHandler taskHandler = new TaskHandler(new JdbcTaskRepository(taskDao));

            httpServer.createContext("/tasks", taskHandler);
            // ----------------------------------------------------

            httpServer.createContext("/health", new HealthHandler());
            ExampleRouter.registerRoutes(httpServer);

            httpServer.start();

            return httpServer;
        }
    }
}