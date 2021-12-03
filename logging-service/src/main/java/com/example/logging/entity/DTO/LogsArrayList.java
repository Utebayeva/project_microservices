package com.example.logging.entity.DTO;

import com.example.logging.entity.Log;
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
