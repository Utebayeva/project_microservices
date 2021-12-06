package com.example.community.entity.DTO;

import com.example.community.entity.Community;
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
