package com.learngql.graphql_playground.controller.section4;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class NewCustomerController1 {

    private final NewCustomerService1 newCustomerService1;
    private final NewOrderService1 newOrderSevirce;


    public NewCustomerController1(NewCustomerService1 newCustomerService1, NewOrderService1 newOrderSevirce) {
        this.newCustomerService1 = newCustomerService1;
        this.newOrderSevirce = newOrderSevirce;
    }

    @SchemaMapping(typeName = "Query")
    public Flux<NewCustomer1> getAllNewCustomers1() {
        return newCustomerService1.getAllNewCustomers();

    }
 

    @BatchMapping(typeName = "NewCustomer1", field = "orders1")
    public Flux<List<NewCustomerOrderDto1>> orders1(List<NewCustomer1> list) {
        System.out.println("Orders invoked for list : " + list.size() + " list data :" + list);
        return newOrderSevirce.getOrderByCustomerNames(list.stream().map(NewCustomer1::name).toList());
    }


    /// i have just created this method and just reg=tuning the ags as 100 .... So if i start my grpQL and see for my Customers the age is been set to 100 for all the customers, althoght the data is correct
    ///
    /// How ????? this is been overriding the stuff
    ///
    /// If you go to our first example we like a sayHello and radom so client send requests to the sever each and every filed is resolved my execting a funciton
    /// that is how the grpahQL works
    /// Nothing but graphQL checks if the sayHello is there the method then go head and exeucte and give it thats all when it stops is if its got scalar stype i,e string int and ... it stops
    ///
    ///
    /// Now when we send this request
    ///
    /// {
    ///   getAllNewCustomers1{
    ///     id,
    ///     name,
    ///     age,
    ///     orders1{
    ///       id,
    ///       description
    ///     }
    ///   }
    /// }
    ///
    /// it will check who will give me the customers data so i have this getAllNewCustomers1 method it will execute that and get the data , This is not a scalar type this is ibject type
    /// althout the NewCustomer1 object fileds these are some scalar types ... So for each and every scalar type it checks if we have any functions like name funciton or age function and stuff if it found it will call and keep that
    ///this is where it execute the age funciton .... if you change the type of function to age1 or something so it will not override
    @SchemaMapping(typeName = "NewCustomer1")
    public Mono<Integer> age() {
        return Mono.just(100);
    }



}
