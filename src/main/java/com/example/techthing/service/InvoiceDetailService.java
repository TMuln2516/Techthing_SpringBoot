package com.example.techthing.service;

import org.springframework.stereotype.Service;

import com.example.techthing.entity.CartDetail;
import com.example.techthing.entity.Invoice;
import com.example.techthing.entity.InvoiceDetail;
import com.example.techthing.entity.Product;
import com.example.techthing.repository.InvoiceDetailRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceDetailService {
        InvoiceDetailRepository invoiceDetailRepo;

        // create
        public void create(Invoice invoice, CartDetail productItem) {
                // create Invoice
                Product product = productItem.getProduct();

                InvoiceDetail invoiceDetail = InvoiceDetail.builder()
                                .invoice(invoice)
                                .product(product)
                                .quantity(productItem.getQuantity())
                                .subTotal(productItem.getQuantity() * product.getPrice())
                                .build();

                this.invoiceDetailRepo.save(invoiceDetail);
        }
}
