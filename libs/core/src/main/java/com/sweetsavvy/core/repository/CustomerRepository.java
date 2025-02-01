package com.sweetsavvy.core.repository;

import com.sweetsavvy.core.entity.Customer;
import com.sweetsavvy.core.model.FilteredCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = """
            SELECT c.id, c.name, c.email, c.image_url,
            COUNT(i.id) AS total_invoices,
            SUM(CASE WHEN i.status = 'pending' THEN i.amount ELSE 0 END) AS total_pending,
            SUM(CASE WHEN i.status = 'paid' THEN i.amount ELSE 0 END) AS total_paid
            FROM customers c
            LEFT JOIN invoices i ON c.id = i.customer_id
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) OR
            LOWER(c.email) LIKE LOWER(CONCAT('%', :query, '%'))
            GROUP BY c.id, c.name, c.email, c.image_url
            ORDER BY c.name ASC;
            """, nativeQuery = true)
    Page<FilteredCustomer> searchCustomers(@Param("query") String query, Pageable pageable);
}
