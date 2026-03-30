package com.classagenda.features.user.domain.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class User {
    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;

    // 1. Constructor COMPLETO: Se usa cuando sacamos datos que ya existen en la Base de Datos.
    public User(Long id, String name, String email, LocalDateTime createdAt) {
        // Antes de guardar los datos, los obligamos a pasar por la "aduana" de validación
        validateName(name);
        validateEmail(email);
        validateCreatedAt(createdAt);

        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    // 2. Constructor PARA NUEVOS USUARIOS: Se usa cuando alguien se registra de cero.
    public User(String name, String email) {
        // Usamos la palabra 'this()' para llamar internamente al constructor principal de arriba.
        // Le pasamos un ID 'null' y generamos la fecha y hora de este mismo instante.
        // TRUCO PRO: Truncamos la fecha a SEGUNDOS (.truncatedTo). SQL Server usa el tipo DATETIME
        // que no es tan preciso como Java (que guarda nanosegundos). Si no igualamos precisiones
        // recortando los milisegundos, los tests de comparación de fechas fallarán.
        this(null, name, email, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
    private void validateName(String nameToValidate) {
        if (nameToValidate == null || nameToValidate.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nameToValidate.length() > 80) {
            throw new IllegalArgumentException("El nombre no puede superar los 80 caracteres.");
        }
    }

    private void validateEmail(String emailToValidate) {
        if (emailToValidate == null || emailToValidate.isBlank()) {
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío.");
        }
        if (emailToValidate.length() > 255) {
            throw new IllegalArgumentException("El correo no puede superar los 255 caracteres.");
        }
        if (!emailToValidate.contains("@")) {
            throw new IllegalArgumentException("El correo debe tener un formato válido (contener '@').");
        }
    }

    private void validateCreatedAt(LocalDateTime dateToValidate) {
        if (dateToValidate == null) {
            throw new IllegalArgumentException("La fecha de creación no puede ser nula.");
        }
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDateTime getCreatedAt() { return createdAt; }

}