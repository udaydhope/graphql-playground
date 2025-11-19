package com.learngql.graphql_playground.controller.section1;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class HelloWordController {

    //Herer we are using reactuive stuff hope you got it
    //1. In the above you are using the sayHello endpoint , Suppose if you change it to
    // sayHelloTest but in the schema.graphqls its ttill the sayHello ... If you just refresh it and hit sayHello it doenset give any error
    // unlike in rest you get the 404 ...in graphsql you jst get the null

    //Ifyou wanted to change the method name the  we can put the mapiing in Querymapping("sayHello")
    @QueryMapping("sayHello")
    public Mono<String> sayHelloTest() {
        return Mono.just("Hello world")
                .delayElement(Duration.ofMillis(900)); // Simulating a delay of 900 milliseconds
    }

    //2. How clint can pass the arguments to the backednServer
    @QueryMapping("sayHelloTo")
    public Mono<String> sayHelloTo(@Argument("name") String value) {
        //return Mono.just("Hello world to you " + name);
        return Mono.fromSupplier(() -> "Hello world to you " + value)
                .delayElement(Duration.ofMillis(1000)); // Simulating a delay of 1000 milliseconds
    }


    @QueryMapping("randomNumber")
    public Mono<Integer> randomTest() {
        return Mono.just(ThreadLocalRandom.current().nextInt(1,100)).delayElement(Duration.ofMillis(500)); // Simulating a delay of 500 milliseconds
    }



}
