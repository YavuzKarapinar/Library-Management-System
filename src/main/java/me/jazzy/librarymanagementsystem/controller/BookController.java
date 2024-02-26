package me.jazzy.librarymanagementsystem.controller;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.model.Book;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<ResponseModel> registerBook(@RequestBody Book book) {
        return new ResponseEntity<>(registerService.registerBook(book), HttpStatus.OK);
    }
}
