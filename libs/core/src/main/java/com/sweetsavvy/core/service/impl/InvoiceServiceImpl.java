package com.sweetsavvy.core.service.impl;

import com.sweetsavvy.core.model.InvoiceCount;
import com.sweetsavvy.core.model.LatestInvoice;
import com.sweetsavvy.core.repository.InvoiceRepository;
import com.sweetsavvy.core.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository repository;
    @Override
    public List<LatestInvoice> findLatestInvoices() {
        return repository.findLatestInvoices();
    }

    @Override
    public InvoiceCount invoiceCounts() {
        return repository.invoiceCounts();
    }

}
