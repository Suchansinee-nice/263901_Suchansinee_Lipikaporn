package com.example.discountservice.model;


import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Item {

	@NotBlank(message = "name must not be null or empty")
	private String name;

	@NotNull(message = "price must not be null")
	@Positive
	private BigDecimal price;

	@NotNull(message = "quantity must not be null")
	@Positive
	private BigDecimal quantity;

	@NotBlank(message = "category must not be null or empty")
	@Pattern(
			regexp = "Clothing|Accessories|Electronics",
			message = "category must be one of the following values only: Clothing, Accessories, Electronics")
	private String category;

}
