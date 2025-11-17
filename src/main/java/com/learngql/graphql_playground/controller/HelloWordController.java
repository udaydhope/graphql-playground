package com.learngql.graphql_playground.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class HelloWordController {

    //Herer we are using reactuive stuff hope you got it
    @QueryMapping
    public Mono<String> sayHello() {
        return Mono.just("Hello world");
    }
}
