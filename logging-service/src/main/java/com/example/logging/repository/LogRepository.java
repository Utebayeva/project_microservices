package com.example.logging.repository;

import com.example.logging.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    Log findLogByLogId(Long logId);
    Log deleteLogByLogId(Long logId);
}