package com.batching.app.dataloader;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class PetController {

    private final PetRepository petRepository;
    private final VisitRepository visitRepository;

    public PetController(PetRepository petRepository, VisitRepository visitRepository) {
        this.petRepository = petRepository;
        this.visitRepository = visitRepository;
    }

    @QueryMapping
    Mono<Pet> petById(@Argument Long id) {
        return Mono.justOrEmpty(petRepository.findById(id));
    }

    @SchemaMapping(typeName = "Pet")
    Mono<List<Visit>> visits(Pet pet) {
        return Mono.just(visitRepository.findByPetId(pet.id()));
    }

    @SchemaMapping(typeName = "Visit")
    Mono<Vet> veterinarian(Visit visit, DataLoaderRegistry registry) {
        DataLoader<Long, Vet> vetLoader = registry.getDataLoader("vetLoader");
        return Mono.fromFuture(vetLoader.load(visit.vetId()));
    }
}