package com.sweetsavvy.app.controller;

import com.sweetsavvy.core.entity.Invoice;
import com.sweetsavvy.core.model.*;
import com.sweetsavvy.core.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody InvoiceDto invoiceDto, HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.save(invoiceDto));
    }

    @GetMapping
    public ResponseEntity<List<LatestInvoice>> findLatestInvoice(HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.findLatestInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable String id, HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody InvoiceDto invoiceDto, HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.update(id, invoiceDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id, HttpServletRequest servletRequest) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/counts")
    public ResponseEntity<InvoiceCount> count(HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.invoiceCounts());
    }

    @Operation(
            summary = "Search invoices",
            description = "Search invoices with pagination and sorting"
    )
    @GetMapping("/search")
    public ResponseEntity<PageData<FilteredInvoice>> searchInvoice(
            @RequestParam String query,
            @ParameterObject Pageable pageable) {
        var page = service.searchInvoices(query, pageable);
        return ResponseEntity.ok(new PageData<>(page.getNumber(), page.getTotalPages(),
                page.getTotalElements(), page.getContent()));
    }
}
