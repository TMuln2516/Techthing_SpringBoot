package com.example.techthing.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.techthing.dto.response.MapAddressResponse;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.CommuneResponse;
import com.example.techthing.dto.response.DistrictResponse;
import com.example.techthing.dto.response.ProvinceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/province")
public class ProvinceController {

        @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
        @GetMapping("/getAll")
        public ApiResponse<List<ProvinceResponse>> getAllProvince() {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                        Resource resource = new ClassPathResource("province/provinces.json");
                        List<MapAddressResponse> addressResponses = Arrays
                                        .asList(objectMapper.readValue(resource.getInputStream(),
                                                        MapAddressResponse[].class));

                        List<ProvinceResponse> allProvinces = addressResponses.stream()
                                        .map(response -> new ProvinceResponse(response.getProvince_name(),
                                                        response.getProvince_id()))
                                        .distinct()
                                        .sorted(Comparator.comparing(ProvinceResponse::getProvince_id))
                                        .collect(Collectors.toList());

                        return ApiResponse.<List<ProvinceResponse>>builder()
                                        .result(allProvinces)
                                        .build();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }

        @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
        @GetMapping("/district/getAll")
        public ApiResponse<List<DistrictResponse>> getAllDistrict(
                        @RequestParam("province_id") String province_id) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                        Resource resource = new ClassPathResource("province/provinces.json");
                        List<MapAddressResponse> addressResponses = Arrays
                                        .asList(objectMapper.readValue(resource.getInputStream(),
                                                        MapAddressResponse[].class));

                        List<DistrictResponse> allDistrictFilter = addressResponses.stream()
                                        .filter(response -> response.getProvince_id().equals(province_id))
                                        .map(response -> new DistrictResponse(response.getDistrict_name(),
                                                        response.getDistrict_id()))
                                        .distinct()
                                        .sorted(Comparator.comparing(DistrictResponse::getDistrict_id))
                                        .collect(Collectors.toList());

                        return ApiResponse.<List<DistrictResponse>>builder()
                                        .result(allDistrictFilter)
                                        .build();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return null;
        }

        @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
        @GetMapping("/commune/getAll")
        public ApiResponse<List<CommuneResponse>> getAllCommune(
                        @RequestParam("province_id") String province_id,
                        @RequestParam("district_id") String district_id) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                        Resource resource = new ClassPathResource("province/provinces.json");
                        List<MapAddressResponse> addressResponses = Arrays
                                        .asList(objectMapper.readValue(resource.getInputStream(),
                                                        MapAddressResponse[].class));

                        List<CommuneResponse> allCommuneFilter = addressResponses.stream()
                                        .filter(response -> response.getProvince_id().equals(province_id)
                                                        && response.getDistrict_id().equals(district_id))
                                        .map(response -> new CommuneResponse(response.getCommune_name(),
                                                        response.getCommune_id()))
                                        .collect(Collectors.toList());

                        return ApiResponse.<List<CommuneResponse>>builder()
                                        .result(allCommuneFilter)
                                        .build();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return null;
        }
}
