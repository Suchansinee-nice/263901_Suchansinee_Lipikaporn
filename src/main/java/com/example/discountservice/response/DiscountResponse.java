package com.example.discountservice.response;

import java.math.BigDecimal;
import java.util.List;

import com.example.discountservice.model.Item;

import lombok.Data;

@Data
public class DiscountResponse {
	
	private List<Item> items;
	private BigDecimal totalPrice;

}
