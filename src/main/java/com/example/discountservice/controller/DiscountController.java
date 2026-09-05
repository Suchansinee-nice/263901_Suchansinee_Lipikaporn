package com.example.discountservice.controller;

import com.example.discountservice.request.DiscountRequest;
import com.example.discountservice.response.DiscountResponse;
import com.example.discountservice.service.DiscountService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
@Log4j2
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<DiscountResponse> calDiscount(@Valid @RequestBody DiscountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(discountService.calDiscount(request));
    }

}
