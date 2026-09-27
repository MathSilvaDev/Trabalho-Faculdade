package com.math.trabalho.application.cliente.service;

import com.math.trabalho.application.cliente.dto.request.CreateClienteRequest;
import com.math.trabalho.application.cliente.dto.response.ClienteResponse;
import com.math.trabalho.application.cliente.filter.ClienteFilter;
import com.math.trabalho.application.cliente.filter.ClienteSpecification;
import com.math.trabalho.domain.cliente.entity.Cliente;
import com.math.trabalho.domain.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Page<ClienteResponse> findAll(int pageNumber, int pageSize, ClienteFilter filter){
        Specification<Cliente> spec = ClienteSpecification.withFilter(filter);

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,

                Sort.by(Sort.Order.asc("name"))
        );

        return clienteRepository.findAll(spec, pageable)
                .map(this::toResponse);
    }

    public ClienteResponse findById(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente nao encontrado"));

        return toResponse(cliente);
    }

    public ClienteResponse create(CreateClienteRequest request){
        Cliente cliente = new Cliente(
                request.name()
        );

        clienteRepository.save(cliente);

        return toResponse(cliente);
    }

    private ClienteResponse toResponse(Cliente cliente){
        return new ClienteResponse(
                cliente.getId(),
                cliente.getName(),
                cliente.getCreatedAt()
        );
    }
}
