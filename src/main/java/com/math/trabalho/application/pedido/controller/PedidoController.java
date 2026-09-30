package com.math.trabalho.application.pedido.controller;

import com.math.trabalho.application.pedido.dto.request.CreatePedidoRequest;
import com.math.trabalho.application.pedido.dto.request.EditPedidoRequest;
import com.math.trabalho.application.pedido.dto.response.PedidoResponse;
import com.math.trabalho.application.pedido.filter.PedidoFilter;
import com.math.trabalho.application.pedido.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize,
            @ModelAttribute PedidoFilter filter) {
        return ResponseEntity
                .ok(pedidoService.findAll(pageNumber, pageSize, filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok(pedidoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> create(@Valid @RequestBody CreatePedidoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoService.create(request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
