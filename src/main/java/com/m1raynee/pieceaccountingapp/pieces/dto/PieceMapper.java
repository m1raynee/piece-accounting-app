package com.m1raynee.pieceaccountingapp.pieces.dto;

import org.springframework.stereotype.Component;

import com.m1raynee.pieceaccountingapp.boxes.BoxEntity;
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
        if (dto == null)
            return null;

        var entity = new PieceEntity();
        entity.setArticle(dto.article());
        entity.setName(dto.name());
        entity.setAltName(dto.altName());
        entity.setCellHint(dto.cellHint());
        return entity;
    }

    public void updateEntity(PieceEntity entity, PieceUpdateDto dto, BoxEntity box) {
        if (entity == null || dto == null)
            return;

        if (dto.name() != null && !dto.name().isBlank()) {
            entity.setName(dto.name());
        }
        if (dto.altName() != null) {
            entity.setAltName(dto.altName());
        }
        if (dto.cellHint() != null) {
            entity.setCellHint(dto.cellHint());
        }
        if (box != null) {
            entity.setBox(box);
        }
    }

}
