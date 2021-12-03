package com.example.logging.controller;

import com.example.logging.entity.DTO.LogsArrayList;
import com.example.logging.entity.Log;
import com.example.logging.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("")
    public List<Log> findAllLogs() {
        return logService.findAllLogs();
    }

    @PostMapping("/saveLog")
    public void saveLog(@RequestBody Log log) {
        logService.saveLog(log);
    }

    @GetMapping("/logsArrayList")
    public LogsArrayList GetLogsDto() {
        ArrayList<Log> logs = (ArrayList<Log>) logService.findAllLogs();
        LogsArrayList logsArrayList = new LogsArrayList(logs);
        return logsArrayList;
    }
}
