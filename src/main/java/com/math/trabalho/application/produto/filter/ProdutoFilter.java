package com.math.trabalho.application.produto.filter;

import java.math.BigDecimal;

public record ProdutoFilter(
        String name,
        BigDecimal price,
        Boolean inStock
) { }
