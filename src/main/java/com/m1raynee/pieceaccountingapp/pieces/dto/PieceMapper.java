package com.m1raynee.pieceaccountingapp.pieces.dto;

import org.springframework.stereotype.Component;

import com.m1raynee.pieceaccountingapp.pieces.PieceEntity;

@Component
public class PieceMapper {

    public PieceResponseDto toDomain(PieceEntity entity) {
        if (entity == null)
            return null;
        return new PieceResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getAltName(),
                entity.getCellHint(),
                entity.getBox() == null ? null : entity.getBox().getId());
    }

    public PieceEntity toEntity(PieceCreateDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
    }

}
