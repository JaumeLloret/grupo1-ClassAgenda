package com.classagenda.features.task.presentation.handlers;


import com.classagenda.features.task.domain.model.Task;
import com.classagenda.features.task.domain.model.TaskPriority;
import com.classagenda.features.task.domain.model.TaskStatus;
import com.classagenda.features.task.domain.repository.TaskRepository;
import com.classagenda.shared.http.JsonResponses;
import com.classagenda.shared.http.ResponseContract;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public final class TaskHandler implements HttpHandler {

    private final TaskRepository taskRepository;

    // Inyectamos el Repositorio desde el exterior
    public TaskHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            // 1. Extraemos quién está haciendo la petición desde la cabecera de seguridad
            String userIdHeader = exchange.getRequestHeaders().getFirst("X-User-Id");
            if (userIdHeader == null || userIdHeader.isBlank()) {
                JsonResponses.sendJson(exchange, 401, ResponseContract.errorJson("No autorizado", "Falta la cabecera X-User-Id"));
                return;
            }
            Long requestingUserId = Long.parseLong(userIdHeader);

            // 2. Enrutamos según el verbo HTTP
            String method = exchange.getRequestMethod();
            switch (method) {
                case "POST" -> handlePost(exchange, requestingUserId);
                case "GET" -> handleGet(exchange, requestingUserId);
                case "PUT" -> handlePut(exchange, requestingUserId);
                case "DELETE" -> handleDelete(exchange, requestingUserId);
                default -> JsonResponses.sendJson(exchange, 405, ResponseContract.errorJson("Método no permitido", null));
            }
        } catch (SecurityException e) {
            JsonResponses.sendJson(exchange, 403, ResponseContract.errorJson("Prohibido", e.getMessage()));
        } catch (Exception e) {
            JsonResponses.sendJson(exchange, 500, ResponseContract.errorJson("Error interno", e.getMessage()));
        }
    }