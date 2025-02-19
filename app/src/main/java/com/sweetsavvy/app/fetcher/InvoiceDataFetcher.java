package com.sweetsavvy.app.fetcher;

import com.sweetsavvy.core.entity.Invoice;
import com.sweetsavvy.core.model.FilteredInvoice;
import com.sweetsavvy.core.model.PageData;
import com.sweetsavvy.core.service.InvoiceService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceDataFetcher implements DataFetcher<PageData<FilteredInvoice>> {

    private final InvoiceService invoiceService;

    public InvoiceDataFetcher(InvoiceService service) {
        this.invoiceService = service;
    }

    @Override
    public PageData<FilteredInvoice> get(DataFetchingEnvironment environment) {
        int page = environment.getArgumentOrDefault("page", 0);
        int size = environment.getArgumentOrDefault("size", 10);
        String query = environment.getArgumentOrDefault("query", "");
        var result = invoiceService.searchInvoices(query, PageRequest.of(page, size));
        return new PageData<>(result.getNumber(), result.getTotalPages(),
                result.getTotalElements(), result.getContent());
    }
}
