package com.example.frontendservice.entity.DTO;

import com.example.frontendservice.entity.Community;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunitiesArrayList {
    private ArrayList<Community> communities;
}
