package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.BookDTO;
import me.jazzy.librarymanagementsystem.exception.notfound.BookNotFoundException;
import me.jazzy.librarymanagementsystem.model.Book;
import me.jazzy.librarymanagementsystem.model.Category;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public Book findBookById(Long isbn) {
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("There is no such book"));
    }

    public ResponseModel registerBook(BookDTO bookDTO) {

        Category category = categoryService.findCategoryByName(bookDTO.getCategoryName());

        Book book = new Book(
                bookDTO.getPublisher(),
                bookDTO.getSummary(),
                category,
                bookDTO.getProductionYear(),
                bookDTO.getAuthorName()
        );

        bookRepository.save(book);

        return new ResponseModel(
                HttpStatus.OK.value(),
                "New Book Added",
                LocalDateTime.now()
        );
    }
}
