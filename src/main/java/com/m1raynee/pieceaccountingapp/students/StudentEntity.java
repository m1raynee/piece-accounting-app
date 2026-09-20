package com.m1raynee.pieceaccountingapp.students;

import java.util.List;

import com.m1raynee.pieceaccountingapp.loans.LoanEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "requestedLoans", "performedLoans" })
@ToString(exclude = { "requestedLoans", "performedLoans" })
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "requester")
    private List<LoanEntity> requestedLoans;

    @OneToMany(mappedBy = "performer")
    private List<LoanEntity> performedLoans;

    public StudentEntity(String name) {
        this.name = name;
    }

    public String getTagId() {
        return "(STU-%d)".formatted(id);
    }

}