package com.learngql.graphql_playground.controller.section2;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {


    private final Flux<Customer> customers = Flux.just(
            new Customer(1, "John Doe", 30, "New York"),
            new Customer(2, "Jane Smith", 25, "Los Angeles"),
            new Customer(3, "Mike Johnson", 40, "Chicago"),
            new Customer(4, "Emily Davis", 35, "Houston")
    );


    public Flux<Customer> getAllCustomers() {
        return customers;
    }

    public Mono<Customer> getCustomerById(Integer id) {
        return customers.filter(customer -> customer.id().equals(id)).next();
    }

    public Flux<Customer> getCustomerByName(String name) {
        return customers.filter(customer -> customer.name().equalsIgnoreCase(name));
    }

    public Flux<Customer> getCustomersByAgeRange(AgeRangeFilter ageRangeFilter) {
//        return customers.filter(customer -> {
//            boolean isAboveMin = ageRangeFilter.minAge() == null || customer.age() >= ageRangeFilter.minAge();
//            boolean isBelowMax = ageRangeFilter.maxAge() == null || customer.age() <= ageRangeFilter.maxAge();
//            return isAboveMin && isBelowMax;
//        });

        //or

        return customers.filter(customer -> customer.age() >= ageRangeFilter.minAge() && customer.age() <= ageRangeFilter.maxAge());
    }
}
