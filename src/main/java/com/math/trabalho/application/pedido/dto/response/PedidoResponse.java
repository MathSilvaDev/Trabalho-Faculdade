package com.math.trabalho.application.pedido.dto.response;

import java.time.LocalDate;

public record PedidoResponse(
        Long id,
        Long clienteId,
        Long produtoId,
        Integer quantity,
        LocalDate createdAt
) { }
