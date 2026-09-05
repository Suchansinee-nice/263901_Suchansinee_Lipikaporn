package com.example.discountservice.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Seasonal {

	@NotNull(message = "every must not be null")
	private BigDecimal every;

	@NotNull(message = "discount must not be null")
	private BigDecimal discount;

}
