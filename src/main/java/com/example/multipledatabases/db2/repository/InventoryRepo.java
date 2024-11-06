package com.example.multipledatabases.db2.repository;


import com.example.multipledatabases.db2.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findInventoryByInvoiceId(Long invoiceId);

}
