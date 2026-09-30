package com.math.trabalho.application.pedido.filter;

import com.math.trabalho.domain.pedido.entity.Pedido;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PedidoSpecification {

    public static Specification<Pedido> withFilter(PedidoFilter filter) {
        return Specification
                .where(exactClienteId(filter.clienteId()))
                .and(exactProdutoId(filter.produtoId()))
                .and(exactCreatedAt(filter.createdAt()));
    }

    private static Specification<Pedido> exactClienteId(Long clienteId) {
        return (root, query, cb) -> clienteId == null
                ? null
                : cb.equal(root.get("cliente").get("id"), clienteId);
    }

    private static Specification<Pedido> exactProdutoId(Long produtoId) {
        return (root, query, cb) -> produtoId == null
                ? null
                : cb.equal(root.get("produto").get("id"), produtoId);
    }

    private static Specification<Pedido> exactCreatedAt(LocalDate createdAt) {
        return (root, query, cb) -> createdAt == null
                ? null
                : cb.equal(root.get("createdAt"), createdAt);
    }
}
