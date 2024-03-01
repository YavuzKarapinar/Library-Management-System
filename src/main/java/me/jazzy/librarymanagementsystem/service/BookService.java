package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.exception.notfound.BookNotFoundException;
import me.jazzy.librarymanagementsystem.model.Book;
import me.jazzy.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book findBookById(Long isbn) {
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("There is no such book"));
    }
}
