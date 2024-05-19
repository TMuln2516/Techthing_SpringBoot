package com.example.techthing.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.techthing.dto.request.ProductItemRequest;
import com.example.techthing.dto.request.CreateCategoryRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.CategoryResponse;
import com.example.techthing.dto.response.GetCartResponse;
import com.example.techthing.dto.response.InvoiceDetailResponse;
import com.example.techthing.service.InvoiceDetailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/invoice-details")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceDetailController {
    InvoiceDetailService invoiceDetailService;

}
