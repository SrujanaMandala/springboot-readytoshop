package com.springlearning.springbootreadytoshop.controller;

import com.springlearning.springbootreadytoshop.dto.Purchase;
import com.springlearning.springbootreadytoshop.dto.PurchaseResponse;
import com.springlearning.springbootreadytoshop.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }


    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }

}
