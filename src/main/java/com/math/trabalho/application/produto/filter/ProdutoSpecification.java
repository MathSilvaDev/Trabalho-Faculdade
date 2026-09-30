package com.math.trabalho.application.produto.filter;

import com.math.trabalho.domain.produto.entity.Produto;
import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpecification {

    public static Specification<Produto> withFilter(ProdutoFilter filter) {
        return Specification
                .where(containsNameIgnoreCase(filter.name()))
                .and(exactPrice(filter.price()))
                .and(exactInStock(filter.inStock()));
    }

    private static Specification<Produto> containsNameIgnoreCase(String name) {
        return (root, query, cb) -> name == null
                ? null
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<Produto> exactPrice(java.math.BigDecimal price) {
        return (root, query, cb) -> price == null
                ? null
                : cb.equal(root.get("price"), price);
    }

    private static Specification<Produto> exactInStock(Boolean inStock) {
        return (root, query, cb) -> inStock == null
                ? null
                : cb.equal(root.get("inStock"), inStock);
    }
}
