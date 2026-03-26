package com.classagenda.features.user.data.mapper;

import com.classagenda.features.user.data.local.entity.UserEntity;
import com.classagenda.features.user.domain.model.User;

public final class UserMapper {
    // Hacemos el constructor privado para bloquear a cualquiera que intente instanciarlo con 'new'
    private UserMapper() {}

    // TRADUCTOR A JAPONÉS: Convierte el Dominio a Entity para poder guardarlo en BD
    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    // TRADUCTOR A ESPAÑOL: Convierte la Entity a Dominio para devolvérselo al programa seguro
    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;
        // ¡Atención! Al instanciar User, los datos pasan automáticamente por la "Aduana"
        // de validaciones (el constructor de User) garantizando su seguridad.
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCreatedAt()
        );
    }
}