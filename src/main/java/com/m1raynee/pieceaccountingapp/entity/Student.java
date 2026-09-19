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
@EqualsAndHashCode(exclude = { "requestedLoans", "performedLoans" })
@ToString(exclude = { "requestedLoans", "performedLoans" })
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
    private List<Loan> requestedLoans;

    @OneToMany(mappedBy = "performer", fetch = FetchType.LAZY)
    private List<Loan> performedLoans;

    public Student(String name) {
        this.name = name;
    }

    public String getTagId() {
        return "(STU-%d)".formatted(id);
    }

}