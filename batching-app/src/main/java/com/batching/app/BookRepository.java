package com.batching.app;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

// Simulate repositories (in-memory)
@Repository
class BookRepository {

    private final List<Book> books = Arrays.asList(
            new Book(1L, "The Lord of the Rings", 1L),
            new Book(2L, "The Hobbit", 1L),
            new Book(3L, "Harry Potter and the Sorcerer's Stone", 2L),
            new Book(4L, "Harry Potter and the Chamber of Secrets", 2L)
    );

    public List<Book> findAll() {
        return books;
    }

    public Book findById(Long id) {
        return books.stream().filter(book -> book.id().equals(id)).findFirst().orElse(null);
    }
}