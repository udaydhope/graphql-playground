package com.learngql.graphql_playground.controller.section3;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class NewCustomerService {

    private final Flux<NewCustomer> newCustomerFlux = Flux.just(
            new NewCustomer(1, "Alice", 30, "NewYork"),
            new NewCustomer(2, "Bob", 25, "Miami"),
            new NewCustomer(3, "Charlie", 35, "Florida"),
            new NewCustomer(4, "Diana", 28, "Boston")
    );

    public Flux<NewCustomer> getAllNewCustomers() {
        return newCustomerFlux;
    }



}
