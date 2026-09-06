package com.example.discountservice.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Coupon {

	@NotBlank(message = "type must not be null or empty")
	@Pattern(
			regexp = "Fixed amount|Percentage discount",
			message = "type must be either 'Fixed amount' or 'Percentage discount' only")
	private String type;

	private BigDecimal amount;
	private BigDecimal percentage;

	@AssertTrue(message = "amount must not be null and must be greater than 0 when type is 'Fixed amount'")
	private boolean isAmountValid() {
		if (!"Fixed amount".equals(type)) {
			return true;
		}
		return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
	}

	@AssertTrue(message = "percentage must not be sent when type is 'Fixed amount'")
	private boolean isPercentageAbsentWhenFixedAmount() {
		if (!"Fixed amount".equals(type)) {
			return true;
		}
		return percentage == null;
	}

	@AssertTrue(message = "percentage must not be null and must be greater than 0 but not exceed 100 when type is 'Percentage discount'")
	private boolean isPercentageValid() {
		if (!"Percentage discount".equals(type)) {
			return true;
		}
		return percentage != null
				&& percentage.compareTo(BigDecimal.ZERO) > 0
				&& percentage.compareTo(new BigDecimal(100)) <= 0;
	}

	@AssertTrue(message = "amount must not be sent when type is 'Percentage discount'")
	private boolean isAmountAbsentWhenPercentageDiscount() {
		if (!"Percentage discount".equals(type)) {
			return true;
		}
		return amount == null;
	}

}
