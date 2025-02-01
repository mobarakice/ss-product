package com.sweetsavvy.core.model;

import com.sweetsavvy.core.entity.Customer;
import com.sweetsavvy.core.entity.Invoice;
import com.sweetsavvy.core.entity.Revenue;
import com.sweetsavvy.core.entity.UserEntity;

import java.util.List;

public record PrepopulateDto(
        List<UserEntity> users,
        List<Customer> customers,
        List<Invoice> invoices,
        List<Revenue> revenue
) {
}
