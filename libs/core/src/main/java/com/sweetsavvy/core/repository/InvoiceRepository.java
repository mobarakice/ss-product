package com.sweetsavvy.core.repository;

import com.sweetsavvy.core.entity.Invoice;
import com.sweetsavvy.core.model.InvoiceCount;
import com.sweetsavvy.core.model.LatestInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    @Query(value = """
            SELECT invoices.amount, customers.name, customers.image_url, customers.email, invoices.id
            FROM invoices
            JOIN customers ON invoices.customer_id = customers.id
            ORDER BY invoices.date DESC
            LIMIT 5
            """, nativeQuery = true)
    List<LatestInvoice> findLatestInvoices();

    @Query(value = """
            SELECT
                    SUM(CASE WHEN status = 'paid' THEN 1 ELSE 0 END) AS collected,
                    SUM(CASE WHEN status = 'pending' THEN 1 ELSE 0 END) AS pending,
                    COUNT(*) AS total
                FROM invoices
            """, nativeQuery = true)
    InvoiceCount invoiceCounts();
}
