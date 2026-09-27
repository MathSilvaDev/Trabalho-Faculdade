package com.math.trabalho.domain.pedido.entity;

import com.math.trabalho.domain.cliente.entity.Cliente;
import com.math.trabalho.domain.produto.entity.Produto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantity;

    public Pedido(Cliente cliente, Produto produto, Integer quantity){
        this.cliente = cliente;
        this.produto = produto;
        this.quantity = quantity;
    }
}
