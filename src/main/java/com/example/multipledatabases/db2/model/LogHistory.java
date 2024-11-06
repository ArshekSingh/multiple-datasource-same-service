package com.example.multipledatabases.db2.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "log_history")
public class LogHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = 9072631174424397361L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log")
    private String log;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "payload", columnDefinition = "JSON")
    private String payload;
}
