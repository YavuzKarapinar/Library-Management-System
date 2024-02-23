package me.jazzy.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String penaltyReason;
    private LocalDateTime penaltyStartingDate;
    private LocalDateTime penaltyFinishingDate;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;

}
