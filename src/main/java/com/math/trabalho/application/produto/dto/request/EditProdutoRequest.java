package com.math.trabalho.application.produto.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EditProdutoRequest(
        @NotBlank
        String name,

        @NotNull
        @DecimalMin("0.0")
        BigDecimal price
) { }
