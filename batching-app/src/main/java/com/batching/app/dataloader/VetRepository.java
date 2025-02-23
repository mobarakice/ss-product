package com.batching.app.dataloader;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class VetRepository {

    private final List<Vet> vets = Arrays.asList(
            new Vet(1L, "Dr. Smith"),
            new Vet(2L, "Dr. Jones")
    );

    public List<Vet> findAllById(Set<Long> ids) {
        return vets.stream().filter(vet -> ids.contains(vet.id())).collect(Collectors.toList());
    }
    public Vet findById(Long id) {
        return vets.stream().filter(vet -> vet.id().equals(id)).findFirst().orElse(null);
    }
}