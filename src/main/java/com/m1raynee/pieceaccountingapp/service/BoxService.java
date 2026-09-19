package com.m1raynee.pieceaccountingapp.service;

import java.util.List;
import java.util.Optional;

import com.m1raynee.pieceaccountingapp.entity.Box;

public interface BoxService {
    List<Box> findAll();
    Optional<Box> findById(Long id);
    Optional<Box> findByIndex(Integer index);
    Box save(Box box);
    void deleteById(Long id);
    void deleteByIndex(Integer index);
    List<Box> findByNameContaining(String name);
}
