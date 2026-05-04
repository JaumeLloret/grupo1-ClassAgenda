package com.classagenda.features.event.domain.model;

import java.time.LocalDateTime;

public final class Event {
    private final Long id;
    private final String title;
    private final String description;
    private final String event_type ;
    private final LocalDateTime start_at;
    private final LocalDateTime end_at;
    private final Long owner_user_id;
    private final LocalDateTime created_at;

    public Event(Long id, String title, String description, String event_type , LocalDateTime start_at, LocalDateTime end_at, Long owner_user_id, LocalDateTime created_at) {
        validateDates(start_at, end_at); // Validación de intervalo temporal
        validateTitle(title);

        this.id = id;
        this.title = title;
        this.description = description;
        this.event_type  = event_type ;
        this.start_at = start_at;
        this.end_at = end_at;
        this.owner_user_id = owner_user_id;
        this.created_at = created_at;
    }

    private void validateDates(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
        }
        // REGLA DE ORO: El fin no puede ser anterior al inicio.
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio.");
        }
        // REGLA EXTRA: Un evento no puede durar 0 segundos.
        if (end.isEqual(start)) {
            throw new IllegalArgumentException("El evento debe tener una duración mínima.");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("El título es obligatorio.");
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getevent_type () {
        return event_type ;
    }

    public LocalDateTime getstart_at() {
        return start_at;
    }

    public LocalDateTime getend_at() {
        return end_at;
    }

    public Long getowner_user_id() {
        return owner_user_id;
    }

    public LocalDateTime getcreated_at() {
        return created_at;
    }
}