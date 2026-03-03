package com.classagenda.shared.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerConfigTest {
    @Test
    void returnsDefaultPortWhenValueIsMissingOrBlank() {
        // Verifica que sin datos o con espacios, da el puerto por defecto (8080)
        assertEquals(8080, ServerConfig.parsePortOrDefault(null));
        assertEquals(8080, ServerConfig.parsePortOrDefault(" "));
    }

    @Test
    void returnsDefaultPortWhenValueIsNotValidNumber() {
        // Verifica que con texto o números fuera de rango, no se rompe
        assertEquals(8080, ServerConfig.parsePortOrDefault("hola"));
        assertEquals(8080, ServerConfig.parsePortOrDefault("70000"));
    }

    @Test
    void returnsConfiguredPortWhenValueIsValid() {
        // Verifica que lee correctamente un puerto válido
        assertEquals(3000, ServerConfig.parsePortOrDefault("3000"));
    }
}
