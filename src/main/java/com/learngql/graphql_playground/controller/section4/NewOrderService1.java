package com.learngql.graphql_playground.controller.section4;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NewOrderService1 {

    //Out of 4 customers only two customer have the orders other dont have it just refrer the newCustomerSveirce Data
    private final Map<String, List<NewCustomerOrderDto1>> customerOrders = Map.of(
            "Alice", List.of(
                    new NewCustomerOrderDto1(UUID.randomUUID(), "Order 1 for Alice"),
                    new NewCustomerOrderDto1(UUID.randomUUID(), "Order 2 for Alice")
            ),
            "Bob", List.of(
                    new NewCustomerOrderDto1(UUID.randomUUID(), "Order 1 for Bob")
            )
    );


   //In herer wrote this method to get orders by customer name lets think of this OrderSevrice as some seperate microsevrice
    public Flux<NewCustomerOrderDto1> getOrdersByCustomerName(String name) {
        List<NewCustomerOrderDto1> orders = customerOrders.getOrDefault(name, List.of());
        return Flux.fromIterable(orders);
    }

    public Flux<List<NewCustomerOrderDto1>> getOrdersForCustomers(List<NewCustomer1> customers) {
        System.out.println("getOrdersForCustomers invoked for list : " + customers.size() + " list data :" + customers);
        return Flux.fromIterable(customers)
                .map(customer -> customerOrders.getOrDefault(customer.name(), List.of()));
    }


    //There is one more issue here actually herer the we get the cistomer size is 4 .... fo i need 4 of the orders else we send it as default
    //But if you use the flatMap and set only what was there then we get some error like --- The size of the Promozed values must be same size as key list so we need to set as default or null

    //as Below
    public Flux<List<NewCustomerOrderDto1>> getOrdersForCustomers_FlatMap_Issue(List<NewCustomer1> customers) {
        System.out.println("getOrdersForCustomers invoked for list : " + customers.size() + " list data :" + customers);
        return Flux.fromIterable(customers)
                .flatMap(customer -> Flux.fromIterable(customerOrders.getOrDefault(customer.name(), List.of())))
                .collectList()
                .flux();
    }



    //Also there is one more issue while actually fetching the data from the microsevrice we may have to call the microsevrice for each customer or it may have some delay right
    // lets make that delayElement from our side and see how it works

   public Flux<List<NewCustomerOrderDto1>> getOrderByCustomerNames(List<String> names) {
        return Flux.fromIterable(names)
                .flatMapSequential(name -> fetchOrders(name).defaultIfEmpty(Collections.emptyList()));
   }

   //I am just deplying some time to get return that from map...
   public Mono<List<NewCustomerOrderDto1>> fetchOrders(String name) {
        //Simulating delay here
        return Mono.justOrEmpty(customerOrders.get(name))
                .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(0,500))); // Simulating variable delay between 0 to 500 milliseconds
   }

   //If you see the response it will be wired ...... ITS A VERY BIG ISSUE SOME DATA WERE TOTALLY INCORRECT , This is pirticular with
    //The reactive types over

    //what we are doing we are getting the list<names> and calling the flatmap by passing another sources ... So flatMap does the things in  parellal but not by sequence like how its like
    //1 st item received fro 1 st customer and 2nd item received for second customer and so on ... but here the delay is random so some times 2nd customer data may come first and so on

    //Just use the flatMapSeuqntial it works asexpected that what i have done Now it fized the issue

//    {
//        "data": {
//        "getAllNewCustomers1": [
//        {
//            "id": "1",
//                "name": "Alice",
//                "age": 30,
//                "orders1": []
//        },
//        {
//            "id": "2",
//                "name": "Bob",
//                "age": 25,
//                "orders1": []
//        },
//        {
//            "id": "3",
//                "name": "Charlie",
//                "age": 35,
//                "orders1": [
//            {
//                "id": "0abb1f65-0578-44f1-b973-2a1bdb543e9e",
//                    "description": "Order 1 for Bob"
//            }
//        ]
//        },
//        {
//            "id": "4",
//                "name": "Diana",
//                "age": 28,
//                "orders1": [
//            {
//                "id": "2eadd8cc-6a2e-4ef0-b60c-c7bc56b9219a",
//                    "description": "Order 1 for Alice"
//            },
//            {
//                "id": "c36da271-758a-4afc-9e5d-b7f645c5ffdc",
//                    "description": "Order 2 for Alice"
//            }
//        ]
//        }
//    ]
//    }
//    }







}
