package com.math.trabalho.domain.pedido.repository;

import com.math.trabalho.domain.pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>,
                                          JpaSpecificationExecutor<Pedido> {
}
