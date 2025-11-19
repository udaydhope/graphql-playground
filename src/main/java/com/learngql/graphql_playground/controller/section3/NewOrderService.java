package com.learngql.graphql_playground.controller.section3;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NewOrderService {

    //Out of 4 customers only two customer have the orders other dont have it just refrer the newCustomerSveirce Data
    private final Map<String, List<NewCustomerOrderDto>> customerOrders = Map.of(
            "Alice", List.of(
                    new NewCustomerOrderDto(UUID.randomUUID(), "Order 1 for Alice"),
                    new NewCustomerOrderDto(UUID.randomUUID(), "Order 2 for Alice")
            ),
            "Bob", List.of(
                    new NewCustomerOrderDto(UUID.randomUUID(), "Order 1 for Bob")
            )
    );


   //In herer wrote this method to get orders by customer name lets think of this OrderSevrice as some seperate microsevrice
    public Flux<NewCustomerOrderDto> getOrdersByCustomerName(String name) {
        List<NewCustomerOrderDto> orders = customerOrders.getOrDefault(name, List.of());
        return Flux.fromIterable(orders);
    }


}
