package me.jazzy.librarymanagementsystem.repository;

import me.jazzy.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
