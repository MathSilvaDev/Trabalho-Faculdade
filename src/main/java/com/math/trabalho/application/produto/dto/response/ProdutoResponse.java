package com.math.trabalho.application.produto.dto.response;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String name,
        BigDecimal price,
        boolean inStock
) { }
