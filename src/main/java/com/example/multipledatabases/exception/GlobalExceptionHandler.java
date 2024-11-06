package com.example.multipledatabases.exception;


import com.example.multipledatabases.db1.model.Invoice;
import com.example.multipledatabases.db1.service.InvoiceService;
import com.example.multipledatabases.db2.service.InventoryService;
import com.example.multipledatabases.dto.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
@ResponseBody
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final InvoiceService invoiceService;

    private final InventoryService inventoryService;

    @ExceptionHandler(UserNotFoundException.class)
    public Response<String> handleUserNotFound(UserNotFoundException ex) {
        log.error("User not found exception occurred : {}", ex.getMessage());
        return new Response<>(HttpStatus.NOT_FOUND, "User not found");
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public Response<String> handleInvalidCredentials(InvalidCredentialsException ex) {
        log.error("Invalid credential exception occurred : {}", ex.getMessage());
        return new Response<>(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    @ExceptionHandler(TenantNotFoundException.class)
    public Response<String> handleTenantNotFound(TenantNotFoundException ex) {
        log.error("Tenant not found exception occurred : {}", ex.getMessage());
        return new Response<>(HttpStatus.NOT_FOUND, "Tenant not found");
    }

    @ExceptionHandler(SystemException.class)
    public Response<String> handleSystemException(SystemException ex) {
        log.error("System exception occurred : {}", ex.getMessage());
        return new Response<>(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response<String> handleGeneralException(Exception ex) {
        log.error("Exception occurred : {}", ex.getMessage());
        return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    @ExceptionHandler(BadCredentialsException.class)
        public Response<String> handleBadCredential(BadCredentialsException ex) {
        log.error("Bad credentials exception occurred : {}", ex.getMessage());
            return new Response<>(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
        }

    @ExceptionHandler(RuntimeException.class)
    public Response<String> handleRunTimeException(RuntimeException ex) {
        log.error("Runtime exception occurred : {}", ex.getMessage());
        return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Response<String> handleIllegalArg(IllegalArgumentException ex) {
        log.error("Illegal Argument exception occurred : {}", ex.getMessage());
        return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR, "Illegal Argument exception occurred");
    }

    @ExceptionHandler(SQLException.class)
    public Response<String> handleSQLException(SQLException ex) {
        log.error("SQL Exception occurred : {}", ex.getMessage());
        return new Response<>(HttpStatus.BAD_REQUEST, "SQL exception occurred");
    }

    @ExceptionHandler(SQLCustomException.class)
    public Response<String> handleCustomSQLException(SQLCustomException ex) {
        log.error("Custom SQL exception occurred : {}", ex.getMessage());
        invoiceService.saveLogHistory(ex.getMessage(), ex.getData());
        inventoryService.saveLogHistory(ex.getMessage(), ex.getData());

        return new Response<>(ex.getStatus(), "Custom SQL exception occurred");
    }
}
