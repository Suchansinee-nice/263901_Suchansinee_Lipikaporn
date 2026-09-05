package com.example.discountservice.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class OnTop {

	@NotBlank(message = "type must not be null or empty")
	@Pattern(
			regexp = "Percentage discount|Discount by points",
			message = "type must be either 'Percentage discount' or 'Discount by points' only")
	private String type;

	private String category;
	private BigDecimal amount;
	private BigDecimal point;

	@AssertTrue(message = "category must not be null or empty when type is 'Percentage discount'")
	private boolean isCategoryPresent() {
		if (!"Percentage discount".equals(type)) {
			return true;
		}

		if (category == null || category.isBlank()) {
			return false;
		}

		return true;
	}

	@AssertTrue(message = "category must be one of the following values only: Clothing, Accessories, Electronics")
	private boolean isCategoryValid() {
		if (!"Percentage discount".equals(type)) {
			return true;
		}

		if (category == null) {
			return true;
		}

		if (!category.equals("Clothing") && !category.equals("Accessories") && !category.equals("Electronics")) {
			return false;
		}

		return true;
	}

	@AssertTrue(message = "amount must not be null and must be greater than 0 when type is 'Percentage discount'")
	private boolean isAmountValid() {
		if (!"Percentage discount".equals(type)) {
			return true;
		}
		return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
	}

	@AssertTrue(message = "point must not be sent when type is 'Percentage discount'")
	private boolean isPointAbsentWhenPercentageDiscount() {
		if (!"Percentage discount".equals(type)) {
			return true;
		}
		return point == null;
	}

	
	
	
	
	@AssertTrue(message = "point must not be null and must be greater than 0 when type is 'Discount by points'")
	private boolean isPointValid() {
		if (!"Discount by points".equals(type)) {
			return true;
		}
		return point != null && point.compareTo(BigDecimal.ZERO) > 0;
	}

	@AssertTrue(message = "amount must not be sent when type is 'Discount by points'")
	private boolean isAmountAbsentWhenDiscountByPoints() {
		if (!"Discount by points".equals(type)) {
			return true;
		}
		return amount == null;
	}

	@AssertTrue(message = "category must not be sent when type is 'Discount by points'")
	private boolean isCategoryAbsentWhenDiscountByPoints() {
		if (!"Discount by points".equals(type)) {
			return true;
		}
		return category == null;
	}

}
