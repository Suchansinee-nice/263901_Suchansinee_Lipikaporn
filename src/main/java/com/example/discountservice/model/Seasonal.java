package com.example.discountservice.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Seasonal {
	
	private BigDecimal every;
	private BigDecimal discount;

}
