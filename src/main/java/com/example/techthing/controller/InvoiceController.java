package com.example.techthing.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techthing.dto.request.UpdateInvoiceRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.InvoiceResponse;
import com.example.techthing.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // create - user
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user")
    ApiResponse<InvoiceResponse> create() {
        return ApiResponse.<InvoiceResponse>builder()
                .result(this.invoiceService.create())
                .build();
    }

    // get all - admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    ApiResponse<List<InvoiceResponse>> getAll() {
        return ApiResponse.<List<InvoiceResponse>>builder()
                .result(this.invoiceService.getAll())
                .build();
    }

    // get one - admin or user
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/admin-user/{id}")
    ApiResponse<InvoiceResponse> getOne(@PathVariable("id") String id) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(this.invoiceService.getOne(id))
                .build();
    }

    // update - admin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")
    ApiResponse<InvoiceResponse> update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(this.invoiceService.update(updateInvoiceRequest))
                .build();
    }

    // delete - admin
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    ApiResponse<Void> deleteUser(@PathVariable("id") String id) {
        this.invoiceService.delete(id);
        return ApiResponse.<Void>builder()
                .build();
    }

    // get all by user - user
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    ApiResponse<List<InvoiceResponse>> getAllByUser() {
        return ApiResponse.<List<InvoiceResponse>>builder()
                .result(this.invoiceService.getAllByUser())
                .build();
    }

}
