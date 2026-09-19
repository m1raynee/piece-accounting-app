package com.m1raynee.pieceaccountingapp.service;

import java.util.List;
import java.util.Optional;

import com.m1raynee.pieceaccountingapp.entity.Piece;

public interface PieceService {
   List<Piece> findAll();
   List<Piece> findAllWithActiveLoans();
   Optional<Piece> findById(Long id);
   Piece save(Piece piece);
   void deleteById(Long id);
   List<Piece> findByNameContaining(String name);
   List<Piece> findByAltNameContaining(String altName);
   List<Piece> findByBoxId(Long boxId);
}
