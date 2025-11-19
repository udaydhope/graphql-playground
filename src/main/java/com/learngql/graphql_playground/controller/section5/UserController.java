package com.learngql.graphql_playground.controller.section5;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class UserController {

    @QueryMapping(value = "users")
    public Flux<User> users() {
        return Flux.just(
                new User(1, "Sam", "sam@gmail.com"),
                new User(2, "Mike", "mike@gmail.com"),
                new User(3, "Jake", "jake@gmail.com")
        );
    }
}
