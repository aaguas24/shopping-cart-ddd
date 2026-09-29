package com.tuempresa.shoppingcart.domain.catalog.aggregated;

import java.util.UUID;

import lombok.Getter;

@Getter
public class AggregateRoot {
    private final UUID id;

    public AggregateRoot(UUID id) {
        this.id = id;
    }
}
