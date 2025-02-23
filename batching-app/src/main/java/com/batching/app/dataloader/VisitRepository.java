package com.batching.app.dataloader;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VisitRepository {

    private final List<Visit> visits = Arrays.asList(
            new Visit(1L, 1L, 1L),
            new Visit(2L, 1L, 2L),
            new Visit(3L, 2L, 1L)
    );

    public List<Visit> findByPetId(Long petId) {
        return visits.stream().filter(visit -> visit.petId().equals(petId)).collect(Collectors.toList());
    }
}
