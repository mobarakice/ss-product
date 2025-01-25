package com.sweetsavvy.core.service.impl;

import com.sweetsavvy.core.entity.Customer;
import com.sweetsavvy.core.repository.CustomerRepository;
import com.sweetsavvy.core.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Customer findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No customer found"));
    }

    @Override
    public Long totalCustomersCount() {
        return repository.count();
    }
}
