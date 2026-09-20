package com.m1raynee.pieceaccountingapp.boxes;

import java.util.List;

import com.m1raynee.pieceaccountingapp.pieces.PieceEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boxes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "pieces" })
public class BoxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer index;

    @Column(nullable = false)
    private String name;

    @Column(name = "place_code", length = 6)
    private String placeCode;

    @OneToMany(mappedBy = "box")
    private List<PieceEntity> pieces;

    public BoxEntity(Integer index, String name, String placeCode) {
        this.index = index;
        this.name = name;
        this.placeCode = placeCode;
    }

    @Override
    public String toString() {
        return getTagId() + " " + name;
    }

    public String getTagId() {
        var result = "(BOX-" + index;
        if (placeCode != null)
            result += ", " + placeCode;
        return result + ")";
    }

}
