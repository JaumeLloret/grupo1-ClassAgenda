package com.classagenda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerConfigTest {

    @Test
    void testPuertoValido() {
        ServerConfig config = new ServerConfig();
        Assertions.assertEquals(3000, config.parsePort("3000"));
    }

    @Test
    void testPuertoInvalido() {
        ServerConfig config = new ServerConfig();
        Assertions.assertEquals(8080, config.parsePort("no-numero"));
        Assertions.assertEquals(8080, config.parsePort("-1"));
        Assertions.assertEquals(8080, config.parsePort("70000"));
        Assertions.assertEquals(8080, config.parsePort(""));
        Assertions.assertEquals(8080, config.parsePort(null));
    }
}


