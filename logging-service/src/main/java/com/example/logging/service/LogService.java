package com.example.logging.service;

import com.example.logging.entity.Log;
import com.example.logging.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;


    public List<Log> findAllLogs() {
        return logRepository.findAll();
    }


    public Log findLogById(Long logId) {
        return logRepository.findLogByLogId(logId);
    }

    public Log saveLog(Log log) {
        return logRepository.save(log);
    }


    public Log deleteLog(Long logId) {
        return logRepository.deleteLogByLogId(logId);
    }
}
