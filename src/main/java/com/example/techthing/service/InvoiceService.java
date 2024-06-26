package com.example.techthing.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.techthing.dto.request.CreateInvoiceRequest;
import com.example.techthing.dto.request.UpdateInvoiceRequest;
import com.example.techthing.dto.response.InvoiceResponse;
import com.example.techthing.dto.response.UserResponse;
import com.example.techthing.entity.CartDetail;
import com.example.techthing.entity.Invoice;
import com.example.techthing.entity.User;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.CartDetailRepository;
import com.example.techthing.repository.InvoiceRepository;
import com.example.techthing.repository.UserRepository;

@Service
public class InvoiceService {
        private final InvoiceRepository invoiceRepo;
        private final UserRepository userRepo;
        private final CartDetailRepository cartDetailRepo;
        private final InvoiceDetailService invoiceDetailService;

        public InvoiceService(InvoiceRepository invoiceRepo, UserRepository userRepo,
                        InvoiceDetailService invoiceDetailService, CartDetailRepository cartDetailRepo) {
                this.invoiceRepo = invoiceRepo;
                this.userRepo = userRepo;
                this.cartDetailRepo = cartDetailRepo;
                this.invoiceDetailService = invoiceDetailService;

        }

        // create
        public InvoiceResponse create(CreateInvoiceRequest createInvoiceRequest) {
                // create Invoice
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = this.userRepo.findByUsername(username)
                                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
                // Cart cart = user.getCart();
                // this.cartRepo.findByUser(user)
                // .orElseThrow(() -> new MyException(ErrorCode.CART_NOT_EXISTED));

                // create invoice
                Invoice invoice = Invoice.builder()
                                .user(user)
                                .shippingInfo(createInvoiceRequest.getShippingInfo())
                                .timeOrder(new Timestamp(System.currentTimeMillis()))
                                .status("PENDING")
                                .build();

                this.invoiceRepo.save(invoice);

                // create invoice details
                for (String productItemId : createInvoiceRequest.getCartDetailIds()) {
                        // find product item object
                        CartDetail productItem = this.cartDetailRepo.findById(productItemId)
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_ITEM_NOT_EXISTED));
                        // add to invoice
                        this.invoiceDetailService.create(invoice, productItem);
                        // remove from cart
                        this.cartDetailRepo.deleteById(productItemId);
                }

                return InvoiceResponse.builder()
                                .id(invoice.getId())
                                .shippingInfo(invoice.getShippingInfo())
                                .timeOrder(invoice.getTimeOrder())
                                .status(invoice.getStatus())
                                .user(UserResponse.builder()
                                                .id(invoice.getUser().getId())
                                                .fullname(invoice.getUser().getFullname())
                                                .username(invoice.getUser().getUsername())
                                                .phone(invoice.getUser().getPhone())
                                                .mail(invoice.getUser().getMail())
                                                .build())
                                .build();
        }

        // get all
        public List<InvoiceResponse> getAll() {
                List<Invoice> invoices = this.invoiceRepo.findAll();
                List<InvoiceResponse> invoiceResponses = new ArrayList<>();

                for (Invoice invoice : invoices) {
                        InvoiceResponse item = InvoiceResponse.builder()
                                        .id(invoice.getId())
                                        .timeOrder(invoice.getTimeOrder())
                                        .status(invoice.getStatus())
                                        .user(UserResponse.builder()
                                                        .id(invoice.getUser().getId())
                                                        .fullname(invoice.getUser().getFullname())
                                                        .username(invoice.getUser().getUsername())
                                                        .phone(invoice.getUser().getPhone())
                                                        .mail(invoice.getUser().getMail())
                                                        .build())
                                        .build();
                        invoiceResponses.add(item);
                }

                return invoiceResponses;
        }

        // get one
        public InvoiceResponse getOne(String id) {
                Invoice invoice = this.invoiceRepo.findById(id)
                                .orElseThrow(() -> new MyException(ErrorCode.INVOICE_NOT_EXISTED));

                return InvoiceResponse.builder()
                                .id(invoice.getId())
                                .timeOrder(invoice.getTimeOrder())
                                .status(invoice.getStatus())
                                .user(UserResponse.builder()
                                                .id(invoice.getUser().getId())
                                                .fullname(invoice.getUser().getFullname())
                                                .username(invoice.getUser().getUsername())
                                                .phone(invoice.getUser().getPhone())
                                                .mail(invoice.getUser().getMail())
                                                .build())
                                .build();
        }

        // update
        public InvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest) {

                Invoice invoice = this.invoiceRepo.findById(updateInvoiceRequest.getId())
                                .orElseThrow(() -> new MyException(ErrorCode.INVOICE_NOT_EXISTED));

                invoice.setStatus(updateInvoiceRequest.getStatus());
                this.invoiceRepo.save(invoice);

                return InvoiceResponse.builder()
                                .id(invoice.getId())
                                .timeOrder(invoice.getTimeOrder())
                                .status(invoice.getStatus())
                                .user(UserResponse.builder()
                                                .id(invoice.getUser().getId())
                                                .fullname(invoice.getUser().getFullname())
                                                .username(invoice.getUser().getUsername())
                                                .phone(invoice.getUser().getPhone())
                                                .mail(invoice.getUser().getMail())
                                                .build())
                                .build();
        }

        // delete
        public void delete(String id) {
                if (!this.invoiceRepo.existsById(id)) {
                        throw new MyException(ErrorCode.INVOICE_NOT_EXISTED);
                }

                this.invoiceRepo.deleteById(id);
        }

        // get all
        public List<InvoiceResponse> getAllByUser() {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = this.userRepo.findByUsername(username)
                                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

                List<Invoice> invoices = this.invoiceRepo.findByUser(user);
                List<InvoiceResponse> invoiceResponses = new ArrayList<>();

                for (Invoice invoice : invoices) {
                        InvoiceResponse item = InvoiceResponse.builder()
                                        .id(invoice.getId())
                                        .timeOrder(invoice.getTimeOrder())
                                        .status(invoice.getStatus())
                                        .user(UserResponse.builder()
                                                        .id(invoice.getUser().getId())
                                                        .fullname(invoice.getUser().getFullname())
                                                        .username(invoice.getUser().getUsername())
                                                        .phone(invoice.getUser().getPhone())
                                                        .mail(invoice.getUser().getMail())
                                                        .build())
                                        .build();
                        invoiceResponses.add(item);
                }

                return invoiceResponses;
        }
}
