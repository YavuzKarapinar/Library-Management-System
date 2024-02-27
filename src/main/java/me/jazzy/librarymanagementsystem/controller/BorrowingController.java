package me.jazzy.librarymanagementsystem.controller;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.BorrowingDTO;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.service.BorrowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrowings")
@AllArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<ResponseModel> newBorrowing(@RequestBody BorrowingDTO borrowingDTO) {
        return new ResponseEntity<>(borrowingService.borrowingBook(borrowingDTO), HttpStatus.OK);
    }

}
