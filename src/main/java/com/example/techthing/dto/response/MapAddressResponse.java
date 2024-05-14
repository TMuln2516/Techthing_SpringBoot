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
public class MapAddressResponse {
    String province_name;
    String province_id;
    String district_name;
    String district_id;
    String commune_name;
    String commune_id;
}
