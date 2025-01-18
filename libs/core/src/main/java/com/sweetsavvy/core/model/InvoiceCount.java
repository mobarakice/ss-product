package com.sweetsavvy.core.model;

public interface InvoiceCount {
    long getCollected();

    long getPending();

    long getTotal();
}