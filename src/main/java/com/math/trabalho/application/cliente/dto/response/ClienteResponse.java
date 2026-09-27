package com.math.trabalho.application.cliente.dto.response;

import java.time.LocalDate;

public record ClienteResponse(
        Long id,
        String name,
        LocalDate createdAt
) { }
