package com.example.techthing.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetCartResponse {
    String cartDetail_id;
    String product_id;
    String product_name;
    String image;
    double price;
    int quantity;
    double sub_total;
}
