package com.math.trabalho.application.cliente.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EditClienteRequest(
        @NotBlank
        String name
) { }
