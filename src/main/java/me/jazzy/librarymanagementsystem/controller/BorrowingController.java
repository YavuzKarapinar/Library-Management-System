package me.jazzy.librarymanagementsystem.controller;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.BorrowingDTO;
import me.jazzy.librarymanagementsystem.model.Borrowing;
import me.jazzy.librarymanagementsystem.model.BorrowingState;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.service.BorrowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/borrowings")
@AllArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<ResponseModel> newBorrowing(@RequestBody BorrowingDTO borrowingDTO) {
        return new ResponseEntity<>(borrowingService.borrowingBook(borrowingDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateBorrowingState(@PathVariable Long id, @RequestBody String borrowingState) {
        borrowingService.updateBorrowingState(id, BorrowingState.valueOf(borrowingState));

        ResponseModel responseModel = new ResponseModel(
                HttpStatus.OK.value(),
                "Borrowing state updated. New borrowing state is: " + borrowingState,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrowing> findBorrowingById(@PathVariable Long id) {
        return new ResponseEntity<>(borrowingService.findBorrowingById(id), HttpStatus.OK);
    }

}
