package com.classagenda;

public class ServerConfig {

    public int parsePort(String value) {
        if (value == null || value.isBlank()) {
            return 8080;
        }
        try {
            int port = Integer.parseInt(value);
            if (port <= 0 || port > 65535) {
                return 8080;
            }
            return port;
        } catch (NumberFormatException e) {
            return 8080;
        }
    }
}
