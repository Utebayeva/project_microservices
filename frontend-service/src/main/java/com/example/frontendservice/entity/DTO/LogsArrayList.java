package com.example.frontendservice.entity.DTO;

import com.example.frontendservice.entity.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogsArrayList {
    private ArrayList<Log> logs;
}
