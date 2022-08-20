package com.springlearning.springbootreadytoshop.dao;

import com.springlearning.springbootreadytoshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
