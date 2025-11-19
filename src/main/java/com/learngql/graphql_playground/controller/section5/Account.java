package com.learngql.graphql_playground.controller.section5;

import java.util.UUID;

public record Account(UUID accountNumber , AccountType type, Integer amount) {
}

enum AccountType {
    SAVINGS,
    CURRENT
}
