package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.BorrowingDTO;
import me.jazzy.librarymanagementsystem.model.*;
import me.jazzy.librarymanagementsystem.repository.BookRepository;
import me.jazzy.librarymanagementsystem.repository.BorrowingRepository;
import me.jazzy.librarymanagementsystem.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ResponseModel borrowingBook(BorrowingDTO borrowingDTO) {

        User user = userRepository.findById(borrowingDTO.getUserId())
                .orElseThrow(() -> new IllegalStateException("There is no such user"));

        Book book = bookRepository.findById(borrowingDTO.getIsbn())
                .orElseThrow(() -> new IllegalStateException("There is no such book"));

        Borrowing borrowing = new Borrowing(
                BorrowingState.PENDING,
                LocalDateTime.now(),
                user,
                book
        );

        borrowingRepository.save(borrowing);

        return new ResponseModel(
                HttpStatus.OK.value(),
                "New Borrowing Added",
                LocalDateTime.now()
        );
    }

}
