package com.classagenda;

import com.classagenda.shared.http.HttpServerBootstrap;

public class App {
    public static void main(String[] args) throws Exception {
        // Instancia y arranca el servidor HTTP
        new HttpServerBootstrap().start();
    }
}