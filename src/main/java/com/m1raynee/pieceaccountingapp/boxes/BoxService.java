package com.m1raynee.pieceaccountingapp.boxes;

import java.util.List;
import java.util.Optional;

public interface BoxService {
    List<BoxEntity> findAll();
    Optional<BoxEntity> findById(Long id);
    Optional<BoxEntity> findByIndex(Integer index);
    BoxEntity save(BoxEntity box);
    void deleteById(Long id);
    void deleteByIndex(Integer index);
    List<BoxEntity> findByNameContaining(String name);
}
