package com.classagenda.shared.http;

public final class JsonEscaper {
    private JsonEscaper() {}

    public static String escape(String rawText) {
        // Reemplaza comillas para no romper el formato JSON
        if (rawText == null) return "";
        return rawText.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}