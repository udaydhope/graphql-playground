package com.learngql.graphql_playground.controller.section3;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class NewCustomerController {

    private final NewCustomerService newCustomerService;
    private final NewOrderService newOrderSevirce;


    public NewCustomerController(NewCustomerService newCustomerService, NewOrderService newOrderSevirce) {
        this.newCustomerService = newCustomerService;
        this.newOrderSevirce = newOrderSevirce;
    }

    //@QueryMapping("getNewCustomers")

    //instead of  this QueryMapping instead we can have this as well , How this works is that there is a NewCustomer field that was in the query
    // as here in the schema we have defined type Query { getNewCustomers: [NewCustomer!]! } so here the typeName is Query
    //Query is the root object herer bacases of that reason that in the argumnet we are not passing anything query is root object and root onject is null
    // you can get the Argumenet but the root object is diffrent beacuse

    //If you see the NewCustomerOrdder in the schema its not root object , this NewCustomerOrder belongs to the NewCustomer , Since this NewCustomer belongs to Query the same way the NewCustomerOrder belongs to NewCustomer ,
    // Now in the orders method i will use this like
//    @SchemaMapping(
//            typeName = "NewCustomer"
//    )

    @SchemaMapping(typeName = "Query")
    public Flux<NewCustomer> getAllNewCustomers() {
        return newCustomerService.getAllNewCustomers();
        //Herer in the schema we have this getNewCustomers: [NewCustomer!]! which will only the newCustomers only through which he can access the order infirmation

        // as you have below
//        type NewCustomer {
//            id : ID
//            name: String
//            age: Int
//            city: String
//            orders : [NewCustomerOrder]! # if you see this this custoemr has list of order by in our java class it need not be as like this
//
//        }



        //NOTE ---- Now what we would have done in case of REST , In GrephQL we have single endpoint to get the NewCustomer and its NewCustomnerOrder details stuff
        // So in REST we would have created another endpoint to get the orders by customer name from OrderService but in GraphQL we can do it in single endpoint only
        // In Rest world we would have autworred the NewOrderSevirce

//        return newCustomerService.getAllNewCustomers().map(customer -> orderSevirce
//                .ordersByCustomerName(customer.name()));

        //Like this above way we should have done and also we should have added the NewCustoemrOrder in the NewCustomer class and add that stuff


    }


//    @SchemaMapping(typeName = "NewCustomer")
//    //Now using this NewCustomer we can ask for this method stuff...
//    public Flux<NewCustomerOrderDto> orders(NewCustomer newCustomer) {
//        System.out.println("Orders invoked for customer: " + newCustomer.name());
//        return newOrderSevirce.getOrdersByCustomerName(newCustomer.name());
//    }

    @SchemaMapping(typeName = "NewCustomer")
    //Now using this NewCustomer we can ask for this method stuff...
    public Flux<NewCustomerOrderDto> orders(NewCustomer newCustomer, @Argument("limit") Integer limit) {
        System.out.println("Orders invoked for customer: " + newCustomer.name());
        return newOrderSevirce.getOrdersByCustomerName(newCustomer.name()).take(limit);
    }




}
