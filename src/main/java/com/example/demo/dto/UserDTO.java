package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String mail;
    private String password;

    public UserDTO(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }
}
