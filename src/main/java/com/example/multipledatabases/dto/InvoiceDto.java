package com.example.multipledatabases.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -2789478301846645465L;
    private Long id;
    private String name;
    private Long productId;
    private Integer quantity;
    private String status;

}
