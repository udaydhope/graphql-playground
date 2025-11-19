package com.learngql.graphql_playground.controller.section2;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {


    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryMapping("getCustomers")
    public Flux<Customer> getAllCustomer() {
       return customerService.getAllCustomers();
    }

    @QueryMapping("getCustomerById")
    public Mono<Customer> getCustomerById(@Argument("id") Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @QueryMapping("getCustomersByName")
    public Flux<Customer> getCustomerByName(@Argument("name") String customerName) {
        return customerService.getCustomerByName(customerName);
    }

    @QueryMapping("getCustomersByAgeRange")
    public Flux<Customer> getCustomersByAgeRange(@Argument("ageByRangeFilter") AgeRangeFilter ageFilter) {
        return customerService.getCustomersByAgeRange(ageFilter);
    }
}
