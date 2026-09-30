package com.math.trabalho.application.produto.controller;

import com.math.trabalho.application.produto.dto.request.CreateProdutoRequest;
import com.math.trabalho.application.produto.dto.request.EditProdutoRequest;
import com.math.trabalho.application.produto.dto.response.ProdutoResponse;
import com.math.trabalho.application.produto.filter.ProdutoFilter;
import com.math.trabalho.application.produto.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize,
            @ModelAttribute ProdutoFilter filter) {

        return ResponseEntity
                .ok(produtoService.findAll(pageNumber, pageSize, filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> create(@Valid @RequestBody CreateProdutoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.create(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> editById(@PathVariable Long id,
                                         @Valid @RequestBody EditProdutoRequest request) {
        produtoService.editById(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/toggle-in-stock")
    public ResponseEntity<Void> toggleInStock(@PathVariable Long id) {
        produtoService.toggleInStock(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
