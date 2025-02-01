package com.sweetsavvy.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweetsavvy.core.entity.Customer;
import com.sweetsavvy.core.model.PrepopulateDto;
import com.sweetsavvy.core.repository.CustomerRepository;
import com.sweetsavvy.core.repository.InvoiceRepository;
import com.sweetsavvy.core.repository.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JsonDataLoader {

    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;
    private final RevenueRepository revenueRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        jsonLoad();
    }

    private void jsonLoad(){
        try {
            var objectMapper = new ObjectMapper();
            var inputStream = new ClassPathResource("db.json").getInputStream();
            var data = objectMapper.readValue(inputStream, PrepopulateDto.class);
            final Map<String, Customer> customerMap = customerRepository.saveAll(data.customers()).stream()
                    .collect(Collectors.toMap(Customer::getId, customer -> customer));
            var invoices = data.invoices()
                    .stream().peek(invoice -> invoice.setCustomer(customerMap.get(invoice.getCustomer_id())))
                    .toList();
            invoiceRepository.saveAll(invoices);
            revenueRepository.saveAll(data.revenue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}