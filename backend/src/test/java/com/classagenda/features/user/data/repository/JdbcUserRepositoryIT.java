package com.classagenda.features.user.data.repository;

import com.classagenda.features.example.data.local.connection.DbConnectionFactory;
import com.classagenda.features.user.data.local.dao.UserDao;
import com.classagenda.features.user.domain.model.User;
import com.classagenda.shared.config.DbConfig;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUserRepositoryIT {
    private JdbcUserRepository userRepository;

    @BeforeEach
    void setUp() {
        // EL ESCUDO PROTECTOR DE CI/CD
        try {
            DbConfig.url(); // Intentamos forzar la lectura del .env
        } catch (Exception e) {
            // Si salta un error (por ejemplo, porque estamos en la máquina virtual de GitHub),
            // cancelamos el test pacíficamente para que no rompa la subida del código.
            Assumptions.abort("Saltando test de integración: No hay BD configurada (.env).");
        }

        // Si el código sobrevive y llega hasta aquí, significa que SÍ hay un .env local válido.
        // Inicializamos la fábrica, el DAO y finalmente el Repositorio.
        userRepository = new JdbcUserRepository(new UserDao(new DbConnectionFactory()));
    }

    @Test
    void savesNewUserAndFindsItByEmail() {
        // Generamos un correo único basado en la hora actual para que el test no choque con
        // ejecuciones anteriores (ya que el email en la BD tiene la restricción UNIQUE).
        String uniqueEmail = "test_" + System.currentTimeMillis() + "@ejemplo.com";
        User userToSave = new User("Prueba Integración", uniqueEmail);

        // ACTUAMOS: Ejecutamos el guardado en la base de datos
        User savedUser = userRepository.save(userToSave);

        // COMPROBACIONES (ASSERT): Verificamos que el usuario vuelve con un ID real
        assertNotNull(savedUser.getId(), "El usuario debería tener un ID de base de datos");
        assertEquals("Prueba Integración", savedUser.getName());
        assertEquals(uniqueEmail, savedUser.getEmail());

        // SEGUNDO ACTO: Volvemos a pedirle a la base de datos que busque ese correo
        Optional<User> foundUserOpt = userRepository.findByEmail(uniqueEmail);
        assertTrue(foundUserOpt.isPresent(), "Deberíamos poder encontrar el usuario");

        User foundUser = foundUserOpt.get(); // Extraemos el usuario de la caja de seguridad
        assertEquals(savedUser.getId(), foundUser.getId());

        // IMPORTANTE: Comparamos las fechas truncándolas a segundos para evitar los falsos
        // errores producidos por la diferencia de precisión entre Java y SQL Server.
        assertEquals(
                savedUser.getCreatedAt().truncatedTo(ChronoUnit.SECONDS),
                foundUser.getCreatedAt().truncatedTo(ChronoUnit.SECONDS)
        );
    }
}