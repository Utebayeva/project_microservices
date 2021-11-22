package com.example.logging.controller;

import com.example.logging.entity.Log;
import com.example.logging.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("")
    public List<Log> findAllLogs() {
        return logService.findAllLogs();
    }

    @PostMapping("/saveLog")
    public Log saveLog(@RequestBody Log log) {
        return logService.saveLog(log);
    }
}
