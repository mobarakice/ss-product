package com.sweetsavvy.app.config;

import com.sweetsavvy.app.fetcher.InvoiceGraphQLService;
import com.sweetsavvy.core.model.SearchInvoiceDto;
import com.sweetsavvy.core.service.InvoiceService;
import graphql.GraphQL;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import graphql.schema.idl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class GraphQLConfig {
    private final ResourceLoader resourceLoader;
    private final InvoiceGraphQLService invoiceService;

    @Bean
    public GraphQL graphQL() throws IOException {
        Resource schemaResource = resourceLoader.getResource("classpath:graphql/schema.graphqls");
        InputStream inputStream = schemaResource.getInputStream();
        String sdl = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(sdl);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("latestInvoice", _ -> invoiceService.latestInvoice())
                        .dataFetcher("filteredInvoice", env -> {
                            var search = (SearchInvoiceDto) env.getSource();
                            assert search != null;
                            return invoiceService.filteredInvoice(search);
                        }))
                .type("Mutation", builder -> builder
                        .dataFetcher("createInvoice", env -> invoiceService.createInvoice(env.getSource())))
                .scalar(ExtendedScalars.GraphQLLong)
                .scalar(ExtendedScalars.Date)
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}