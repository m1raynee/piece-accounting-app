package com.m1raynee.pieceaccountingapp.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "piece_id", nullable = false)
    private Piece piece;

    @ManyToOne()
    @JoinColumn(name = "requester_student_id", nullable = false)
    private Student requester;

    @ManyToOne()
    @JoinColumn(name = "performer_student_id", nullable = false)
    private Student performer;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "edited_at", nullable = false)
    private LocalDateTime editedAt;
    @Column(name = "taken_until")
    private LocalDateTime takenUntil;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        editedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        editedAt = LocalDateTime.now();
    }

    public Loan(Piece piece, Student requester, Student performer, Integer amount, LoanStatus status) {
        this.piece = piece;
        this.requester = requester;
        this.performer = performer;
        this.amount = amount;
        this.status = status;
    }

    public String getTagId() {
        return "(LNS-%d)".formatted(id);
    }

}

enum LoanStatus {
    ACTIVE, RETURNED, LOST
}
