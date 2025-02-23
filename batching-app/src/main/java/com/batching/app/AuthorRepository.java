package com.batching.app;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
class AuthorRepository {

    private final List<Author> authors = Arrays.asList(
            new Author(1L, "J.R.R. Tolkien"),
            new Author(2L, "J.K. Rowling")
    );

    public List<Author> findAll() {
        return authors;
    }

    public List<Author> findAllById(Set<Long> ids) {
        return authors.stream().filter(author -> ids.contains(author.id())).collect(Collectors.toList());
    }

    public Author findById(Long id) {
        return authors.stream().filter(author -> author.id().equals(id)).findFirst().orElse(null);
    }
}