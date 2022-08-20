package com.springlearning.springbootreadytoshop.dto;

import com.springlearning.springbootreadytoshop.entity.Address;
import com.springlearning.springbootreadytoshop.entity.Customer;
import com.springlearning.springbootreadytoshop.entity.Order;
import com.springlearning.springbootreadytoshop.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
