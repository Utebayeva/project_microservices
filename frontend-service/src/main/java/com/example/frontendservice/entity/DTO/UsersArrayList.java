package com.example.frontendservice.entity.DTO;

import com.example.frontendservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersArrayList {
    private ArrayList<User> users;
}
