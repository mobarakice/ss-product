package com.sweetsavvy.core.service;

import com.sweetsavvy.core.model.InvoiceCount;
import com.sweetsavvy.core.model.LatestInvoice;

import java.util.List;

public interface InvoiceService {
    List<LatestInvoice> findLatestInvoices();
    InvoiceCount invoiceCounts();
}
