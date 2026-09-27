package com.math.trabalho.application.cliente.controller;

import com.math.trabalho.application.cliente.dto.response.ClienteResponse;
import com.math.trabalho.application.cliente.filter.ClienteFilter;
import com.math.trabalho.application.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
}
