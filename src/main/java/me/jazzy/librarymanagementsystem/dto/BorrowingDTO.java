package me.jazzy.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BorrowingDTO {
    private Long userId;
    private Long isbn;
}