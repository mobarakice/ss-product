package com.sweetsavvy.core.service;

import com.sweetsavvy.core.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findById(String id);
    Long totalCustomersCount();
}
