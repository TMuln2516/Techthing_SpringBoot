package com.example.techthing.service;

import org.springframework.stereotype.Service;

import com.example.techthing.dto.request.ProductItemRequest;
import com.example.techthing.entity.Invoice;
import com.example.techthing.entity.InvoiceDetail;
import com.example.techthing.entity.Product;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.InvoiceDetailRepository;
import com.example.techthing.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceDetailService {
        InvoiceDetailRepository invoiceDetailRepo;
        ProductRepository productRepo;

        // create
        public void create(Invoice invoice, ProductItemRequest productItemRequest) {
                // create Invoice
                Product product = this.productRepo.findById(productItemRequest.getProductId())
                                .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));

                InvoiceDetail invoiceDetail = InvoiceDetail.builder()
                                .invoice(invoice)
                                .product(product)
                                .quantity(productItemRequest.getQuantity())
                                .subTotal(productItemRequest.getQuantity() * product.getPrice())
                                .build();

                this.invoiceDetailRepo.save(invoiceDetail);
        }
}
