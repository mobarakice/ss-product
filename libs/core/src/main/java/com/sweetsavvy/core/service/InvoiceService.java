package com.sweetsavvy.core.service;

import com.sweetsavvy.core.entity.Invoice;
import com.sweetsavvy.core.model.FilteredInvoice;
import com.sweetsavvy.core.model.InvoiceCount;
import com.sweetsavvy.core.model.InvoiceDto;
import com.sweetsavvy.core.model.LatestInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    Invoice save(InvoiceDto invoiceDto);
    List<LatestInvoice> findLatestInvoices();
    Invoice findById(String id);
    void deleteById(String id);
    Invoice update(String id, InvoiceDto invoiceDto);
    InvoiceCount invoiceCounts();
    Page<FilteredInvoice> searchInvoices(String query, Pageable pageable);
}
