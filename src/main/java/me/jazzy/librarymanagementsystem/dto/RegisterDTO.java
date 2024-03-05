package me.jazzy.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
