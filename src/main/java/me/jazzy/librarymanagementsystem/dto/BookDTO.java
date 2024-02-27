package me.jazzy.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BookDTO {

    private String publisher;
    private String summary;
    private String categoryName;
    private LocalDateTime productionYear;
    private String authorName;
}
