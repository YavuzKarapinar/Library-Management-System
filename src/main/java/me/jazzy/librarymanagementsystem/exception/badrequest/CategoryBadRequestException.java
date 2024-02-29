package me.jazzy.librarymanagementsystem.exception.badrequest;

import me.jazzy.librarymanagementsystem.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class CategoryBadRequestException extends BaseException {
    public CategoryBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
