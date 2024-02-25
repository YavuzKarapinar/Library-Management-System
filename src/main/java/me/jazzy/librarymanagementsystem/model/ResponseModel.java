package me.jazzy.librarymanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ResponseModel {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
