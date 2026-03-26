package com.classagenda.features.user.data.repository;

import com.classagenda.features.user.data.local.dao.UserDao;
import com.classagenda.features.user.data.local.entity.UserEntity;
import com.classagenda.features.user.data.mapper.UserMapper;
import com.classagenda.features.user.domain.model.User;
import com.classagenda.features.user.domain.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class JdbcUserRepository implements UserRepository {
    private final UserDao userDao;

    // Inyectamos el DAO en el constructor
    public JdbcUserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(User userToSave) {
        // 1. Traducción inicial
        UserEntity entityToSave = UserMapper.toEntity(userToSave);

        // 2. Lógica de enrutamiento
        if (entityToSave.getId() == null) {
            // Es un usuario nuevo: Lo insertamos y traducimos la respuesta (que ya trae el ID)
            UserEntity insertedEntity = userDao.insert(entityToSave);
            return UserMapper.toDomain(insertedEntity);
        } else {
            // Ya existe: Lo actualizamos y devolvemos el usuario intacto
            userDao.update(entityToSave);
            return userToSave;
        }
    }
    @Override
    public Optional<User> findByEmail(String email) {
        // En lugar de hacer if/else pesados, la caja Optional tiene un método '.map'.
        // Esto le dice a Java: "Si la caja trae una Entity, aplícale el traductor
        // UserMapper::toDomain dentro de la propia caja y devuélvemela transformada".
        return userDao.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id).map(UserMapper::toDomain);
    }
    @Override
    public List<User> findAll() {
        List<UserEntity> entityList = userDao.findAll();

        // Aplicamos la magia funcional de los Streams
        return entityList.stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        // El borrado no necesita traducción, simplemente enviamos la orden al DAO
        userDao.deleteById(id);
    }
}