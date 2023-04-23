package com.filiaiev.authservice.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

public class NaturalRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements NaturalRepository<T, ID> {

    private final EntityManager entityManager;

    public NaturalRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findBySimpleNaturalId(Serializable naturalId) {
        return entityManager.unwrap(Session.class)
                .bySimpleNaturalId(this.getDomainClass())
                .loadOptional(naturalId);
    }
}
