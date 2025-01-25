package com.sweetsavvy.core.model;

public interface FilteredInvoice extends LatestInvoice {
    String getStatus();
    String getDate();
}
