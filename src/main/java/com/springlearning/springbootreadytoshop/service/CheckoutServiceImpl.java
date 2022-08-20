package com.springlearning.springbootreadytoshop.service;


import com.springlearning.springbootreadytoshop.dao.CustomerRepository;
import com.springlearning.springbootreadytoshop.dto.Purchase;
import com.springlearning.springbootreadytoshop.dto.PurchaseResponse;
import com.springlearning.springbootreadytoshop.entity.Customer;
import com.springlearning.springbootreadytoshop.entity.Order;
import com.springlearning.springbootreadytoshop.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //retrieve order info from dto
        Order order = purchase.getOrder();
        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with orderitems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        //save to database
        customerRepository.save(customer);

        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //generate random UUID number
        return UUID.randomUUID().toString();
    }
}
