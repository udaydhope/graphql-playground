package com.learngql.graphql_playground.controller.section5;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {


    @SchemaMapping(typeName = "User")
    public Mono<Address> address(User user) {
        return Mono.just(new Address(user.name() + " street", user.name() + " city"));
    }
}
