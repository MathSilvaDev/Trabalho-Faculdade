package com.math.trabalho.application.pedido.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EditPedidoRequest(
        @NotNull
        Long clienteId,

        @NotNull
        Long produtoId,

        @NotNull
        @PositiveOrZero
        Integer quantity
) { }
