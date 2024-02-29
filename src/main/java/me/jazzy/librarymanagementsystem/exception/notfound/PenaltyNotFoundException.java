package me.jazzy.librarymanagementsystem.exception.notfound;

import me.jazzy.librarymanagementsystem.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class PenaltyNotFoundException extends BaseException {
    public PenaltyNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
