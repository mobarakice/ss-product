package com.sweetsavvy.core.service;

import com.sweetsavvy.core.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> findAll(Pageable pageable);
    Customer findById(String id);
    Long totalCustomersCount();
}