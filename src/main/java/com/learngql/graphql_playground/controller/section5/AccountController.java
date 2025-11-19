package com.learngql.graphql_playground.controller.section5;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AccountController {

    @SchemaMapping(typeName = "User")
    public Mono<Account> account(User user) {
        var type = ThreadLocalRandom.current().nextBoolean() ? AccountType.SAVINGS : AccountType.CURRENT;
        return Mono.just(new Account(UUID.randomUUID(),type, 1000));
    }

}
