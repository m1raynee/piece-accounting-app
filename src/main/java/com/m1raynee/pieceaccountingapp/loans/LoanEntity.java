package com.m1raynee.pieceaccountingapp.loans;

import java.time.LocalDateTime;
import java.util.List;

import com.m1raynee.pieceaccountingapp.students.StudentEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "loans")
@EqualsAndHashCode(exclude = "stockMovements")
@ToString(exclude = "stockMovements")
@NoArgsConstructor
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "requester_student_id", nullable = false)
    private StudentEntity requester;

    @ManyToOne()
    @JoinColumn(name = "performer_student_id", nullable = true)
    private StudentEntity performer;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "edited_at", nullable = false)
    private LocalDateTime editedAt;

    @Column(name = "taken_until")
    private LocalDateTime takenUntil;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @OneToMany(mappedBy = "loan")
    private List<StockMovementEntity> stockMovements;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        editedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        editedAt = LocalDateTime.now();
    }

    public LoanEntity(
            StudentEntity requester,
            StudentEntity performer,
            LoanStatus status) {
        this.requester = requester;
        this.performer = performer;
        this.status = status;
    }

    public void setStatus(LoanStatus status) {
        if (status == LoanStatus.ACTIVE && performer == null) {
            throw new IllegalStateException("An active loan must have a performer");
        }
        this.status = status;
    }

    public String getTagId() {
        return "(LNS-%d)".formatted(id);
    }

}
