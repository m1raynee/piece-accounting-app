package com.m1raynee.pieceaccountingapp.boxes.dto;

import org.springframework.stereotype.Component;

import com.m1raynee.pieceaccountingapp.boxes.BoxEntity;

@Component
public class BoxMapper {

    public BoxResponseDto toDomain(BoxEntity entity) {
        return new BoxResponseDto(
                entity.getId(),
                entity.getIndex(),
                entity.getName(),
                entity.getPlaceCode());
    }

}
