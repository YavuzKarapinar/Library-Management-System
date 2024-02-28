package me.jazzy.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PenaltyDTO {

    private String reason;
    private LocalDateTime startingDate;
    private Long userId;
}
