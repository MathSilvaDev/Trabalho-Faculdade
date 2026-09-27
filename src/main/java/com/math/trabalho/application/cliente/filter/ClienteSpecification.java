package com.math.trabalho.application.cliente.filter;

import com.math.trabalho.domain.cliente.entity.Cliente;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ClienteSpecification{

    public static Specification<Cliente> withFilter(ClienteFilter filter){
        return Specification
                .where(containsNameIgnoreCaseSensitive(filter.name()))
                .and(exactCreatedAt(filter.createdAt()));
    }

    private static Specification<Cliente> containsNameIgnoreCaseSensitive(String name){
        return (root, query, cb) ->
                name == null
                ? null
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<Cliente> exactCreatedAt(LocalDate createdAt){
        return (root, query, cb) ->
                createdAt == null
                ? null
                : cb.equal(root.get("createdAt"), createdAt);
    }
}
