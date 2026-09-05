package com.example.discountservice.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Coupon {
	
	private String type;
	private BigDecimal amount;
	private BigDecimal percentage;

}
