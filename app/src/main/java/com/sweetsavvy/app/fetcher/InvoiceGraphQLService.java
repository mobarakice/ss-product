package com.sweetsavvy.app.fetcher;

import com.sweetsavvy.core.entity.Invoice;
import com.sweetsavvy.core.model.*;
import com.sweetsavvy.core.service.InvoiceService;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class InvoiceGraphQLService {

    private final InvoiceService invoiceService;

    public InvoiceGraphQLService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @QueryMapping
    public List<LatestInvoice> latestInvoice() {
        var result = invoiceService.findLatestInvoices();
        return result == null? Collections.emptyList(): result;
    }

    @QueryMapping
    public PageData<FilteredInvoice> filteredInvoice(@Argument SearchInvoiceDto input) {
        var result = invoiceService.searchInvoices(input.query(), PageRequest.of(input.page(), input.size()));
        return new PageData<>(result.getNumber(), result.getTotalPages(),
                result.getTotalElements(), result.getContent());
    }

    @MutationMapping
    public Invoice createInvoice(@Argument InvoiceDto input) {
        return invoiceService.save(input);
    }
}
