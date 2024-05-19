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

import com.example.techthing.dto.request.ProductItemRequest;
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
    ApiResponse<InvoiceResponse> create(@RequestBody List<ProductItemRequest> productItemRequests) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(this.invoiceService.create(productItemRequests))
                .build();
    }

    // get all - admin or manager
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/admin-manager")
    ApiResponse<List<InvoiceResponse>> getAll() {
        return ApiResponse.<List<InvoiceResponse>>builder()
                .result(this.invoiceService.getAll())
                .build();
    }

    // get one - admin or manage or user
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @GetMapping("/admin-manager-user/{id}")
    ApiResponse<InvoiceResponse> getOne(@PathVariable("id") String id) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(this.invoiceService.getOne(id))
                .build();
    }

    // update - admin or manager
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/admin-manager")
    ApiResponse<InvoiceResponse> update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(this.invoiceService.update(updateInvoiceRequest))
                .build();
    }

    // delete - admin or manage
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/admin-manager/{id}")
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
