package com.math.trabalho.application.pedido.filter;

import java.time.LocalDate;

public record PedidoFilter(
        Long clienteId,
        Long produtoId,
        LocalDate createdAt
) { }
