package me.jazzy.librarymanagementsystem.exception.notfound;

import me.jazzy.librarymanagementsystem.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class BookNotFoundException extends BaseException {
    public BookNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
