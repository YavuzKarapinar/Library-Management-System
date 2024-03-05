package me.jazzy.librarymanagementsystem.controller;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.AuthResponseDTO;
import me.jazzy.librarymanagementsystem.dto.LoginDTO;
import me.jazzy.librarymanagementsystem.dto.RegisterDTO;
import me.jazzy.librarymanagementsystem.model.ResponseModel;
import me.jazzy.librarymanagementsystem.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(authService.loginUser(loginDTO), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<ResponseModel> register(@RequestBody RegisterDTO request) {
        return new ResponseEntity<>(authService.singUpUser(request), HttpStatus.OK);
    }
}
