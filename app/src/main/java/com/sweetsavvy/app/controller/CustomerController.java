package com.sweetsavvy.app.controller;

import com.sweetsavvy.core.entity.Customer;
import com.sweetsavvy.core.model.FilteredCustomer;
import com.sweetsavvy.core.model.FilteredInvoice;
import com.sweetsavvy.core.model.LatestInvoice;
import com.sweetsavvy.core.model.PageData;
import com.sweetsavvy.core.service.CustomerService;
import com.sweetsavvy.core.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/customers")
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<PageData<Customer>> findAll(@ParameterObject Pageable pageable, HttpServletRequest servletRequest) {
        var page = service.findAll(pageable);
        return ResponseEntity.ok(new PageData<>(page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getContent()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findByIdl(@PathVariable String id, HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/counts")
    public ResponseEntity<Long> count(HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.totalCustomersCount());
    }

    @Operation(
            summary = "Search customer",
            description = "Search customer with pagination and sorting"
    )
    @GetMapping("/search")
    public ResponseEntity<PageData<FilteredCustomer>> searchCustomers(
            @RequestParam String query,
            @ParameterObject Pageable pageable) {
        var page = service.searchCustomers(query, pageable);
        return ResponseEntity.ok(new PageData<>(page.getNumber(), page.getTotalPages(),
                page.getTotalElements(), page.getContent()));
    }

}
