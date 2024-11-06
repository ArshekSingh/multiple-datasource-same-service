package com.example.multipledatabases.controller;


import com.example.multipledatabases.service.DistributiveTransactionService;
import com.example.multipledatabases.dto.InvoiceDto;
import com.example.multipledatabases.dto.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {

    private final DistributiveTransactionService distributiveTransactionService;

    @PostMapping("/createInvoice")
    public Response<String> createInvoice(@RequestBody InvoiceDto invoice) {
        return distributiveTransactionService.createInvoice(invoice);
    }

    @GetMapping("/test")
    public Response<String> test(@RequestParam String name, @RequestParam Long productId, @RequestParam Integer quantity) {
        InvoiceDto invoice1 = new InvoiceDto();
        invoice1.setName(name);
        invoice1.setQuantity(quantity);
        invoice1.setProductId(productId);
        return distributiveTransactionService.createInvoice(invoice1);
    }
}
