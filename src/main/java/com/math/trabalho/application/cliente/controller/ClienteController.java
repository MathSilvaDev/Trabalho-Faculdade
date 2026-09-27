package com.math.trabalho.application.cliente.controller;

import com.math.trabalho.application.cliente.dto.request.CreateClienteRequest;
import com.math.trabalho.application.cliente.dto.response.ClienteResponse;
import com.math.trabalho.application.cliente.filter.ClienteFilter;
import com.math.trabalho.application.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize,
            @ModelAttribute ClienteFilter filter){

        return ResponseEntity
                .ok(clienteService.findAll(pageNumber, pageSize, filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable Long id){
        return ResponseEntity
                .ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> create(
            @Valid @RequestBody CreateClienteRequest request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.create(request));
    }
}
