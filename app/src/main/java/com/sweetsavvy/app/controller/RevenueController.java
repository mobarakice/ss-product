package com.sweetsavvy.app.controller;

import com.sweetsavvy.core.entity.Revenue;
import com.sweetsavvy.core.service.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/revenue")
public class RevenueController {

    private final RevenueService revenueService;

    @GetMapping
    public ResponseEntity<List<Revenue>> findRevenues() {
        return ResponseEntity.ok(revenueService.findAll());
    }

}
