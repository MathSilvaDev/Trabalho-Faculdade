package com.math.trabalho.application.cliente.filter;

import java.time.LocalDate;

public record ClienteFilter(
        String name,
        LocalDate createdAt
) { }
