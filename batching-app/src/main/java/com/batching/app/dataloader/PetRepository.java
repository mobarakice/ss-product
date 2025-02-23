package com.batching.app.dataloader;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
@Repository
public class PetRepository {

    private final List<Pet> pets = Arrays.asList(
            new Pet(1L, "Buddy"),
            new Pet(2L, "Whiskers")
    );

    public Pet findById(Long id) {
        return pets.stream().filter(pet -> pet.id().equals(id)).findFirst().orElse(null);
    }
}