package com.example.frontendservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    private Long logId;
    private Long userId;
    private String service;
    private String action;
    private String description;
}
