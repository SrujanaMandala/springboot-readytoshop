package com.springlearning.springbootreadytoshop.service;

import com.springlearning.springbootreadytoshop.dto.Purchase;
import com.springlearning.springbootreadytoshop.dto.PurchaseResponse;


public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
