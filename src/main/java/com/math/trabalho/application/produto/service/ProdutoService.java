package com.math.trabalho.application.produto.service;

import com.math.trabalho.application.produto.dto.request.CreateProdutoRequest;
import com.math.trabalho.application.produto.dto.request.EditProdutoRequest;
import com.math.trabalho.application.produto.dto.response.ProdutoResponse;
import com.math.trabalho.application.produto.filter.ProdutoFilter;
import com.math.trabalho.application.produto.filter.ProdutoSpecification;
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
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Page<ProdutoResponse> findAll(int pageNumber, int pageSize, ProdutoFilter filter) {
        Specification<Produto> spec = ProdutoSpecification.withFilter(filter);
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize,
                Sort.by(Sort.Order.asc("name"))
        );

        return produtoRepository.findAll(spec, pageable)
                .map(this::toResponse);
    }

    public ProdutoResponse findById(Long id) {
        return toResponse(getProdutoById(id));
    }

    public ProdutoResponse create(CreateProdutoRequest request) {
        Produto produto = new Produto(
                request.name(),
                request.price(),
                request.inStock()
        );

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    @Transactional
    public void editById(Long id, EditProdutoRequest request) {
        getProdutoById(id).edit(request.name(), request.price());
    }

    public void deleteById(Long id) {
        produtoRepository.delete(getProdutoById(id));
    }

    @Transactional
    public void toggleInStock(Long id) {
        getProdutoById(id).toggleInStock();
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getName(),
                produto.getPrice(),
                produto.isInStock()
        );
    }

    private Produto getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }
}
