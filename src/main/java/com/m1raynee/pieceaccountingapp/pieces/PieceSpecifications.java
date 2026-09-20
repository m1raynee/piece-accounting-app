package com.m1raynee.pieceaccountingapp.pieces;

import org.springframework.data.jpa.domain.Specification;

public class PieceSpecifications {

    public static Specification<PieceEntity> hasName(String name) {
        return (root, query, cb) -> (name == null || name.isBlank())
                ? null
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<PieceEntity> hasAltName(String altName) {
        return (root, query, cb) -> (altName == null || altName.isBlank())
                ? null
                : cb.like(cb.lower(root.get("altName")), "%" + altName.toLowerCase() + "%");
    }

    public static Specification<PieceEntity> hasBoxId(Long boxId) {
        return (root, query, cb) -> (boxId == null)
                ? null
                : cb.equal(root.get("box").get("id"), boxId);
    }
}
