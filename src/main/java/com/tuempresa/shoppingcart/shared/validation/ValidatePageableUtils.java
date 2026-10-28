package com.tuempresa.shoppingcart.shared.validation;

import com.tuempresa.shoppingcart.application.catalog.dto.PaginacionDTO;
import com.tuempresa.shoppingcart.shared.enums.OrderPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.Optional;

public final class ValidatePageableUtils {

    public static Pageable validate(PaginacionDTO paginacion){
        int page = paginacion.getPage();
        int size = paginacion.getSize();
        String sort = paginacion.getSort();
        var order = Optional.ofNullable(paginacion.getOrder())
                .map(campo -> campo.getOrden()).orElse(OrderPage.ASCENDENTE.getOrden());

        return Optional.ofNullable(sort)
                .filter(campo -> isValidString(campo))
                .map(campo -> Sort.by(order, campo))
                .map(orden -> PageRequest.of(page, size, orden))
                .orElseGet(() -> PageRequest.of(page, size));
    }

    private static boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
