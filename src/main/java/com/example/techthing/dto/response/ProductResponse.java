package com.example.techthing.dto.response;

import java.util.Set;

import com.example.techthing.entity.CartDetail;
import com.example.techthing.entity.Category;
import com.example.techthing.entity.InvoiceDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String name;
    int quantity;
    double price;
    String image;
    String description;

    CategoryResponse category;

    @JsonManagedReference
    Set<InvoiceDetail> InvoiceDetails;

    @JsonManagedReference
    Set<CartDetail> cartDetails;
}
