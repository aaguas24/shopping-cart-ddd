package com.tuempresa.shoppingcart.application.catalog.dto;

import com.tuempresa.shoppingcart.shared.enums.OrderPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginacionDTO {
    private int page;
    private int size;
    private String sort;
    private OrderPage order;
}
