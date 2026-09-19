package com.m1raynee.pieceaccountingapp.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "loans" })
@ToString(exclude = { "loans" })
public class Piece {

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
    private Box box;

    @Column(name = "cell_hint")
    private String cellHint;

    @OneToMany(mappedBy = "piece")
    private List<Loan> loans;

    @Transient
    private List<Loan> trLoans;

    @Transient
    private Long positiveAmount;
    @Transient
    private Long negativeAmount;

    public Piece(String article, String name, String altName, Box box) {
        this.article = article;
        this.name = name;
        this.altName = altName;
        this.box = box;
    }

    public String getTagId() {
        return "(PCE-%d)".formatted(id);
    }

    public Long getCalculatedAmount() {
        return positiveAmount - negativeAmount;
    }

}
