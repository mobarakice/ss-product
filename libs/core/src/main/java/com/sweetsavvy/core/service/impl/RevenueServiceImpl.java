package com.sweetsavvy.core.service.impl;

import com.sweetsavvy.core.entity.Revenue;
import com.sweetsavvy.core.repository.RevenueRepository;
import com.sweetsavvy.core.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService {
    private final RevenueRepository repository;

    @Override
    public List<Revenue> findAll() {
        return repository.findAll();
    }
}
