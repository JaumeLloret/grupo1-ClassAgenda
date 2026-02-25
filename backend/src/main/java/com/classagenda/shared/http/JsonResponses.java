package com.classagenda.shared.http;

public class JsonResponses {
    public static String success(String mensaje, String datosJson) {
        String dataValue = (datosJson == null || datosJson.isEmpty()) ? "null" : datosJson;
        return String.format(
                "{\"status\": \"success\", \"message\": \"%s\", \"data\": %s}",
                JsonEscaper.escape(mensaje),
                dataValue
        );
    }

    public static String error(String mensaje) {
        return String.format(
                "{\"status\": \"error\", \"message\": \"%s\", \"data\": null}",
                JsonEscaper.escape(mensaje)
        );
    }
}
