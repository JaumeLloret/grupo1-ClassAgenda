package com.classagenda.shared.config;

public final class ServerConfig {
    private static final int DEFAULT_PORT = 8080;

    private ServerConfig() {}

    public static int port() {
        // 1. Intenta leer el puerto de las propiedades del sistema
        String rawPortFromProperty = System.getProperty("CLASSAGENDA_PORT");
        if (rawPortFromProperty != null && !rawPortFromProperty.isBlank()) {
            return parsePortOrDefault(rawPortFromProperty);
        }

        // 2. Si no lo encuentra, lo busca en las variables de entorno
        String rawPortFromEnv = System.getenv("CLASSAGENDA_PORT");
        return parsePortOrDefault(rawPortFromEnv);
    }

    static int parsePortOrDefault(String rawPortValue) {
        if (rawPortValue == null || rawPortValue.isBlank()) {
            return DEFAULT_PORT;
        }
        try {
            // Convierte el texto a número y comprueba que es un puerto válido
            int portNumber = Integer.parseInt(rawPortValue);
            boolean isPortInValidRange = portNumber >= 1 && portNumber <= 65535;
            if (!isPortInValidRange) {
                return DEFAULT_PORT;
            }
            return portNumber;
        } catch (NumberFormatException exception) {
            // Si le pasaron texto en lugar de números, devuelve el puerto por defecto
            return DEFAULT_PORT;
        }
    }
}
