package com.example.discountservice.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.discountservice.model.Coupon;
import com.example.discountservice.model.Item;
import com.example.discountservice.model.OnTop;
import com.example.discountservice.model.Seasonal;
import com.example.discountservice.request.DiscountRequest;
import com.example.discountservice.response.DiscountResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DiscountService {
	
	public DiscountResponse calDiscount(DiscountRequest request) {
		
		BigDecimal runningTotal = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;
		DiscountResponse response = new DiscountResponse();
		response.setItems(request.getItems());
		
		try {
			for(Item item : request.getItems()) {
				
				//price x quantity
				if(item.getQuantity().compareTo(BigDecimal.ONE) > 0) {
					totalPrice = item.getPrice().multiply(item.getQuantity());
				}else {
					totalPrice = item.getPrice();
				}
				runningTotal = runningTotal.add(totalPrice);
			}
			
			if (request.getCoupon() != null) {
			    BigDecimal discount = calculateCoupon(request.getCoupon(), runningTotal); 
			    runningTotal = runningTotal.subtract(discount);
			}

			if (request.getOntop() != null) {
			    BigDecimal discount = calculateOntop(request.getItems(), request.getOntop(), runningTotal); 
			    runningTotal = runningTotal.subtract(discount); 
			}

			if (request.getSeasonal() != null) {
			    BigDecimal discount = calculateSeasonal(request.getSeasonal(), runningTotal);
			    runningTotal = runningTotal.subtract(discount);
			}
			
			response.setTotalPrice(runningTotal);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return response;
		
	}
	
	public BigDecimal calculateCoupon(Coupon coupon, BigDecimal runningTotal) {
		BigDecimal discount = BigDecimal.ZERO;
		if(coupon.getType().equals("Fixed amount")) {
			discount = coupon.getAmount();
		}else {
			discount = runningTotal.multiply(coupon.getPercentage()).divide(new BigDecimal(100));
		}
		return discount;
	}
	
	public BigDecimal calculateOntop(List<Item> items, OnTop onTop, BigDecimal runningTotal) {
		BigDecimal discount = BigDecimal.ZERO;
		BigDecimal totalCategory = BigDecimal.ZERO;
		if(onTop.getType().equals("Percentage discount")) {
			for(Item i : items) {
				if(onTop.getCategory().equals(i.getCategory())) {
					totalCategory = totalCategory.add(i.getPrice());
				}
			}
			
			discount = totalCategory.multiply(onTop.getAmount()).divide(new BigDecimal(100));
		}else {
			BigDecimal max = runningTotal.multiply(new BigDecimal(20)).divide(new BigDecimal(100));
			BigDecimal min = onTop.getPoint();
			
			if(min.compareTo(max) > 0) { //min > max use max
				discount = max;
			}else {
				discount = min;
			}
		}
		return discount;
	}
	
	public BigDecimal calculateSeasonal(Seasonal seasonal, BigDecimal runningTotal) {		
		BigDecimal times = runningTotal.divideToIntegralValue(seasonal.getEvery());
		BigDecimal totalDiscount = times.multiply(seasonal.getDiscount());
		
		return totalDiscount;
	}

}
