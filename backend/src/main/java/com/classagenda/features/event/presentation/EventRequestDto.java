package com.classagenda.features.event.presentation;

public final class EventRequestDto {
    public String title;
    public String description;
    public String event_type;
    public String start_at; // Recibimos el texto del JSON
    public String end_at;   // Recibimos el texto del JSON
}