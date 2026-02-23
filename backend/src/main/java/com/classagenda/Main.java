package com.classagenda;

import com.classagenda.presentation.handlers.HealthHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Configuramos el puerto (usando la clase de tu compañero Iván)
        int port = 8080;

        // 2. Creamos el servidor en ese puerto
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // 3. AQUÍ METES TU LÍNEA (Persona 4):
        server.createContext("/health", new HealthHandler());

        // 4. Arrancamos el servidor
        server.setExecutor(null);
        server.start();

        System.out.println("🚀 Servidor lanzado en el puerto: " + port);
        System.out.println("🔗 Prueba tu ruta en: http://localhost:" + port + "/health");
    }
}
