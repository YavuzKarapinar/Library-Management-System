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

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;
    private String publisher;
    private String summary;
    private LocalDateTime productionYear;
    private String authorName;

    @OneToOne
    @JoinColumn(
            name = "category_id"
    )
    private Category category;

    public Book(String publisher,
                String summary,
                Category category,
                LocalDateTime productionYear,
                String authorName) {
        this.publisher = publisher;
        this.summary = summary;
        this.category = category;
        this.productionYear = productionYear;
        this.authorName = authorName;
    }
}
