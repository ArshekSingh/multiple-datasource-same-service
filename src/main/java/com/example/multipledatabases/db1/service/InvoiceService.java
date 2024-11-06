package com.example.multipledatabases.db1.service;


import com.example.multipledatabases.db1.model.LogHistory;
import com.example.multipledatabases.db1.repository.LogHistoryRepo;
import com.example.multipledatabases.dto.InvoiceDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final LogHistoryRepo logHistoryRepo;

    public InvoiceService(@Qualifier("db1LogHistoryRepo") LogHistoryRepo logHistoryRepo) {
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
