package me.jazzy.librarymanagementsystem.controller;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.model.RegisterRequest;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<ResponseModel> register(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(registerService.singUpUser(request), HttpStatus.OK);
    }
}
