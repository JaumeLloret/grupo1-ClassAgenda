package com.classagenda.features.example.presentation.router;

import com.classagenda.features.example.presentation.handlers.ExampleIntroHandler;
import com.sun.net.httpserver.HttpServer;

public final class ExampleRouter {
    private ExampleRouter() {}

    public static void registerRoutes(HttpServer httpServer) {
        // Vincula la ruta /example/intro con el manejador recién creado
        httpServer.createContext("/example/intro", new ExampleIntroHandler());
    }
}
