package com.example.multipledatabases.db1.repository;

import com.example.multipledatabases.db1.model.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("db1LogHistoryRepo")
public interface LogHistoryRepo extends JpaRepository<LogHistory, Long> {
}
