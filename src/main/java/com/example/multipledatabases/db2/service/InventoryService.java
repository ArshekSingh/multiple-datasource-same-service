package com.example.multipledatabases.db2.service;


import com.example.multipledatabases.db2.model.LogHistory;
import com.example.multipledatabases.db2.repository.LogHistoryRepo;
import com.example.multipledatabases.dto.InvoiceDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final LogHistoryRepo logHistoryRepo;

    public InventoryService(@Qualifier("db2LogHistoryRepo") LogHistoryRepo logHistoryRepo) {
        this.logHistoryRepo = logHistoryRepo;
    }

    public void saveLogHistory(String logMessage, Object data) {
        InvoiceDto invoice = (InvoiceDto) data;
        LogHistory logHistory = LogHistory.builder()
                .payload(new Gson().toJson(invoice))
                .log(logMessage)
                .invoiceId(invoice.getId())
                .transactionType("INVOICE").build();
        logHistoryRepo.save(logHistory);
    }
}
