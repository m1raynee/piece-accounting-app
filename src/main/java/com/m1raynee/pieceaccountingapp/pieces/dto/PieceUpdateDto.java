package com.m1raynee.pieceaccountingapp.pieces.dto;

public record PieceUpdateDto(
        String name,
        String altName,
        String cellHint,
        Long boxId) {
}
