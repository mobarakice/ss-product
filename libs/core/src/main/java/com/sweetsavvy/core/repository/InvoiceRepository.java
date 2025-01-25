package com.sweetsavvy.core.repository;

import com.sweetsavvy.core.entity.Invoice;
import com.sweetsavvy.core.model.FilteredInvoice;
import com.sweetsavvy.core.model.InvoiceCount;
import com.sweetsavvy.core.model.LatestInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>, JpaSpecificationExecutor<Invoice> {
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

    @Query(value = """
            SELECT i.id, i.status, i.amount, i.date, c.name, c.email, c.image_url
            FROM invoices i
            JOIN customers c ON i.customer_id = c.id
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) OR
            LOWER(c.email) LIKE LOWER(CONCAT('%', :query, '%')) OR
            CAST(i.amount AS text) LIKE LOWER(CONCAT('%', :query, '%')) OR
            CAST(i.date AS text) LIKE LOWER(CONCAT('%', :query, '%')) OR
            LOWER(i.status) LIKE LOWER(CONCAT('%', :query, '%'))
            ORDER BY i.date DESC;
            """, nativeQuery = true)
    Page<FilteredInvoice> searchInvoices(@Param("query") String query, Pageable pageable);
}
