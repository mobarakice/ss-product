package com.sweetsavvy.core.repository;

import com.sweetsavvy.core.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, String> {
}