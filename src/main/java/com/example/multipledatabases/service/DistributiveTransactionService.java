package com.example.multipledatabases.service;

import com.example.multipledatabases.db1.model.Invoice;
import com.example.multipledatabases.db1.repository.InvoiceRepo;
import com.example.multipledatabases.db2.model.Inventory;
import com.example.multipledatabases.db2.repository.InventoryRepo;
import com.example.multipledatabases.dto.InvoiceDto;
import com.example.multipledatabases.dto.Response;
import com.example.multipledatabases.exception.SQLCustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DistributiveTransactionService {

    private final InvoiceRepo invoiceRepo;
    private final InventoryRepo inventoryRepo;

    public Response<String> createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice1 = new Invoice();
        invoice1.setName(invoiceDto.getName());
        invoice1.setQuantity(invoiceDto.getQuantity());
        invoice1.setProductId(invoiceDto.getProductId());
        invoice1.setStatus("SUCCESS");
        Invoice invoiceResult = invoiceRepo.save(invoice1);
        invoiceDto.setId(invoice1.getId());
        if ("SUCCESS".equalsIgnoreCase(invoiceResult.getStatus())) {
            Inventory inventoryResult = Inventory.builder().status("SUCCESS")
                    .invoiceId(invoiceResult.getId())
                    .quantity(invoiceResult.getQuantity())
                    .productId(invoiceResult.getProductId()).build();
            try {
                Inventory inventory = inventoryRepo.save(inventoryResult);
            }
            catch (DataIntegrityViolationException e) {
                log.error("Data Integrity Violation Exception occurred : {}", e.getMessage());
                throw new SQLCustomException(HttpStatus.BAD_REQUEST, e.getMessage(), invoiceDto);
//                invoiceResult.setStatus("FAILED");
//                invoiceRepo.save(invoiceResult);
//                log.info("Status marked failed for invoice : {}", invoiceResult.getId());
            }
            catch (RuntimeException e) {
                log.error("Runtime exception occurred : {}", e.getMessage());
                throw new SQLCustomException(HttpStatus.BAD_REQUEST, e.getMessage(), inventoryResult);
            }

//            Optional<Inventory> inventoryByInvoiceId = inventoryRepo.findInventoryByInvoiceId(invoiceResult.getId());
//            inventoryByInvoiceId.ifPresent(o -> {
//                o.setStatus("SUCCESS");
//                inventoryRepo.save(o);
//                log.info("Status marked success for inventory Id : {}", o.getId());
//            });

        }
//        else {
//            invoice.setStatus("FAILED");
//            invoiceRepo.save(invoice);
//        }
        return new Response<>(HttpStatus.OK, "SUCCESS");
    }
}

