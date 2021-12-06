package com.example.frontendservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String nickname;
    private String password;
    private String email;
    private Double wallet;

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
