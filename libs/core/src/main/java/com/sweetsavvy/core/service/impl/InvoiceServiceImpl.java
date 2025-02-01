package com.sweetsavvy.core.service.impl;

import com.sweetsavvy.core.entity.Invoice;
import com.sweetsavvy.core.model.FilteredInvoice;
import com.sweetsavvy.core.model.InvoiceCount;
import com.sweetsavvy.core.model.InvoiceDto;
import com.sweetsavvy.core.model.LatestInvoice;
import com.sweetsavvy.core.repository.CustomerRepository;
import com.sweetsavvy.core.repository.InvoiceRepository;
import com.sweetsavvy.core.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository repository;
    private final CustomerRepository customerRepository;

    @Override
    public Invoice save(InvoiceDto invoice) {
        var customer = customerRepository.findById(invoice.customerId())
                .orElseThrow(()-> new RuntimeException("No customer found"));
        return repository.save(Invoice.builder()
                        .amount(invoice.amount())
                        .status(invoice.status())
                        .customer(customer)
                .build());
    }

    @Override
    public List<LatestInvoice> findLatestInvoices() {
        return repository.findLatestInvoices();
    }

    @Override
    public Invoice findById(String id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No invoice found"));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Invoice update(String id, InvoiceDto invoiceDto) {
        var invoice = repository.findById(id).orElseThrow(() -> new RuntimeException("No invoice found"));
        var customer = customerRepository.findById(invoiceDto.customerId())
                .orElseThrow(() -> new RuntimeException("No customer found"));

        invoice.setCustomer(customer);
        invoice.setAmount(invoice.getAmount());
        invoice.setStatus(invoiceDto.status());
        return repository.save(invoice);
    }

    @Override
    public InvoiceCount invoiceCounts() {
        return repository.invoiceCounts();
    }

    @Override
    public Page<FilteredInvoice> searchInvoices(String query, Pageable pageable) {
        return repository.searchInvoices(query, pageable);
    }
}
