package com.batching.app;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    Flux<Book> books() {
        return Flux.fromIterable(bookRepository.findAll());
    }

    @BatchMapping(typeName = "Book")
    Mono<Map<Book, Author>> author(List<Book> books) {
        Set<Long> authorIds = books.stream().map(Book::authorId).collect(Collectors.toSet());
        List<Author> authors = authorRepository.findAllById(authorIds);

        Map<Long, Author> authorMap = authors.stream()
                .collect(Collectors.toMap(Author::id, author -> author));

        Map<Book, Author> result = books.stream()
                .collect(Collectors.toMap(book -> book, book -> authorMap.get(book.authorId())));

        return Mono.just(result);
    }

    @QueryMapping
    Flux<Author> authors(){
        return Flux.fromIterable(authorRepository.findAll());
    }

    @QueryMapping
    Mono<Book> bookById(@Argument Long id) {
        return Mono.justOrEmpty(bookRepository.findById(id));
    }

    @QueryMapping
    Mono<Author> authorById(@Argument Long id) {
        return Mono.justOrEmpty(authorRepository.findById(id));
    }

}