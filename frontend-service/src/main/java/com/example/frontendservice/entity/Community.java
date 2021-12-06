package com.example.frontendservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {

    private Long communityId;
    private Long userId;
    private Long gameId;
    private String name;
    private String description;
}
