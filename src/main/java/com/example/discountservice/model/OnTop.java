package com.example.discountservice.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OnTop {
	
	private String type;
	private String category;
	private BigDecimal amount;
	private BigDecimal point;

}
