package com.m1raynee.pieceaccountingapp.pieces;

import java.util.List;

import com.m1raynee.pieceaccountingapp.boxes.BoxEntity;
import com.m1raynee.pieceaccountingapp.loans.StockMovementEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "pieces")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "stockMovements" })
@ToString(exclude = { "stockMovements" })
public class PieceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String article;

    @Column(nullable = false)
    private String name;

    @Column(name = "alt_name")
    private String altName;

    @ManyToOne()
    @JoinColumn(name = "box_id")
    private BoxEntity box;

    @Column(name = "cell_hint")
    private String cellHint;

    @OneToMany(mappedBy = "piece")
    private List<StockMovementEntity> stockMovements;

    public PieceEntity(String article, String name, String altName, BoxEntity box) {
        this.article = article;
        this.name = name;
        this.altName = altName;
        this.box = box;
    }

    public String getTagId() {
        return "(PCE-%d)".formatted(id);
    }

}
