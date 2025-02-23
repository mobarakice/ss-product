package com.batching.app.dataloader;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Configuration
public class Config {
    @Bean
    DataLoaderRegistry dataLoaderRegistry(VetRepository vetRepository) {
        DataLoaderRegistry registry = new DataLoaderRegistry();

        MappedBatchLoader<Long, Vet> vetBatchLoader = vetIds ->
                CompletableFuture.supplyAsync(() -> vetRepository.findAllById(new HashSet<>(vetIds)).stream()
                        .collect(Collectors.toMap(Vet::id, vet -> vet)));

        registry.register("vetLoader", DataLoaderFactory.newMappedDataLoader(vetBatchLoader));
        return registry;
    }
}
