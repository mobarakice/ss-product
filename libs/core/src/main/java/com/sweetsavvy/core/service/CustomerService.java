package com.sweetsavvy.core.service;

import com.sweetsavvy.core.entity.Customer;
import com.sweetsavvy.core.model.FilteredCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> findAll(Pageable pageable);

    Customer findById(String id);

    Long totalCustomersCount();

    Page<FilteredCustomer> searchCustomers(String query, Pageable pageable);

}