package com.classagenda.shared.http;

import com.classagenda.features.example.presentation.router.ExampleRouter;
import com.classagenda.shared.config.ServerConfig;
import com.classagenda.shared.http.handlers.HealthHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public final class HttpServerBootstrap {
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