package com.math.trabalho.application.pedido.service;

import com.math.trabalho.application.pedido.dto.request.CreatePedidoRequest;
import com.math.trabalho.application.pedido.dto.request.EditPedidoRequest;
import com.math.trabalho.application.pedido.dto.response.PedidoResponse;
import com.math.trabalho.application.pedido.filter.PedidoFilter;
import com.math.trabalho.application.pedido.filter.PedidoSpecification;
import com.math.trabalho.domain.cliente.entity.Cliente;
import com.math.trabalho.domain.cliente.repository.ClienteRepository;
import com.math.trabalho.domain.pedido.entity.Pedido;
import com.math.trabalho.domain.pedido.repository.PedidoRepository;
import com.math.trabalho.domain.produto.entity.Produto;
import com.math.trabalho.domain.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public Page<PedidoResponse> findAll(int pageNumber, int pageSize, PedidoFilter filter) {
        Specification<Pedido> spec = PedidoSpecification.withFilter(filter);
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize,
                Sort.by(Sort.Order.desc("createdAt"))
        );

        return pedidoRepository.findAll(spec, pageable)
                .map(this::toResponse);
    }

    public PedidoResponse findById(Long id) {
        return toResponse(getPedidoById(id));
    }

    public PedidoResponse create(CreatePedidoRequest request) {
        Pedido pedido = new Pedido(
                getClienteById(request.clienteId()),
                getProdutoById(request.produtoId()),
                request.quantity()
        );
        pedidoRepository.save(pedido);
        return toResponse(pedido);
    }

    public void deleteById(Long id) {
        pedidoRepository.delete(getPedidoById(id));
    }

    private PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getProduto().getId(),
                pedido.getQuantity(),
                pedido.getCreatedAt());
    }

    private Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido nao encontrado"));
    }

    private Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    private Produto getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }
}
