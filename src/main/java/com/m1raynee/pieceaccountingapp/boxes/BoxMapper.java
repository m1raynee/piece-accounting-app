package com.m1raynee.pieceaccountingapp.boxes;

/**
 * BoxMapper
 */
public class BoxMapper {

    public BoxResponseDto toDomain(BoxEntity entity) {
        return new BoxResponseDto(
                entity.getId(),
                entity.getIndex(),
                entity.getName(),
                entity.getPlaceCode());
    }

}
