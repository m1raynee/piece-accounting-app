package com.m1raynee.pieceaccountingapp.repository;

import java.util.NoSuchElementException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    default T findOrThrow(ID id) {
        return findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Entity with id " + id + " not found"));
    }
}