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
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private BorrowingState borrowingState;
    private LocalDateTime borrowingDate;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "book_isbn"
    )
    private Book book;

    @OneToOne
    @JoinColumn(
            name = "category_id"
    )
    private Category category;

    public Borrowing(BorrowingState borrowingState,
                     LocalDateTime borrowingDate,
                     User user,
                     Book book) {
        this.borrowingState = borrowingState;
        this.borrowingDate = borrowingDate;
        this.user = user;
        this.book = book;
    }
}
