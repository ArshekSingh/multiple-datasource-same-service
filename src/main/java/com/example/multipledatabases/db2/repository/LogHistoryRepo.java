package com.example.multipledatabases.db2.repository;

import com.example.multipledatabases.db2.model.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("db2LogHistoryRepo")
public interface LogHistoryRepo extends JpaRepository<LogHistory, Long> {
}
