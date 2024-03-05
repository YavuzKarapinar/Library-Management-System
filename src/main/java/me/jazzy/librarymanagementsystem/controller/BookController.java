package me.jazzy.librarymanagementsystem.controller;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.BookDTO;
import me.jazzy.librarymanagementsystem.model.Book;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.service.BookService;
import me.jazzy.librarymanagementsystem.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ResponseModel> registerBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.registerBook(bookDTO), HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable long isbn) {
        return new ResponseEntity<>(bookService.findBookById(isbn), HttpStatus.OK);
    }
}
