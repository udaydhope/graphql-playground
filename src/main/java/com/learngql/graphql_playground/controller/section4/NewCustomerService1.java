package com.learngql.graphql_playground.controller.section4;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class NewCustomerService1 {

    private final Flux<NewCustomer1> newCustomerFlux = Flux.just(
            new NewCustomer1(1, "Alice", 30, "NewYork"),
            new NewCustomer1(2, "Bob", 25, "Miami"),
            new NewCustomer1(3, "Charlie", 35, "Florida"),
            new NewCustomer1(4, "Diana", 28, "Boston")
    );

    public Flux<NewCustomer1> getAllNewCustomers() {
        return newCustomerFlux;
    }



}
