package com.sweetsavvy.app.controller;

import com.sweetsavvy.core.model.InvoiceCount;
import com.sweetsavvy.core.model.LatestInvoice;
import com.sweetsavvy.core.service.InvoiceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    @GetMapping
    public ResponseEntity<List<LatestInvoice>> findLatestInvoice(HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.findLatestInvoices());
    }

    @GetMapping("/counts")
    public ResponseEntity<InvoiceCount> count(HttpServletRequest servletRequest) {
        return ResponseEntity.ok(service.invoiceCounts());
    }
}
