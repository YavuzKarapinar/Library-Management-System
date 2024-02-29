package me.jazzy.librarymanagementsystem.exception.notfound;

import me.jazzy.librarymanagementsystem.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends BaseException {

    public CategoryNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
