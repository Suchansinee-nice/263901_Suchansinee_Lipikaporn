package com.example.discountservice.request;

import java.util.List;

import com.example.discountservice.model.Coupon;
import com.example.discountservice.model.Item;
import com.example.discountservice.model.OnTop;
import com.example.discountservice.model.Seasonal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class DiscountRequest {

    @NotEmpty(message = "items must not be null or empty")
    private List<@Valid Item> items;

    private Coupon coupon;
    private OnTop ontop;
    private Seasonal seasonal;
    
    @AssertTrue(message = "ต้องระบุแคมเปญอย่างน้อยหนึ่งอย่าง (coupon, ontop หรือ seasonal)")
    private boolean isAtLeastOneCampaignPresent() {
        return coupon != null || ontop != null || seasonal != null;
    }
}
