package com.classagenda.features.event.data.local.dao;

import com.classagenda.features.event.domain.model.Event;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public final class EventDao {
    
    
    private final Connection connection;
    
    
    public EventDao (Connection connection){
        this.connection = connection;
    }
    
    public Event insert(Event entity) throws SQLException {
        String query = "INSERT INTO EVENTS (title, description, location, start_at, end_at, owner_id, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, entity.getTitle());
            pstmt.setString(2, entity.getDescription());
            pstmt.setString(3, entity.getevent_type ());

            // JDBC gestiona LocalDateTime directamente mediante setObject
            pstmt.setObject(4, entity.getstart_at());
            pstmt.setObject(5, entity.getend_at());

            pstmt.setLong(6, entity.getowner_user_id());
            pstmt.setObject(7, entity.getcreated_at());

            pstmt.executeUpdate();
            // ... (resto de lógica para recuperar ID)
        }
        return entity;
    }
}

