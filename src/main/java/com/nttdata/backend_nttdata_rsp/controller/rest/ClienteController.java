package com.nttdata.backend_nttdata_rsp.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.backend_nttdata_rsp.model.dto.ClienteDTO;
import com.nttdata.backend_nttdata_rsp.service.IClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<ClienteDTO> obtenerCliente(
            @RequestParam String tipoDocumento,
            @RequestParam String numeroDocumento) {
        // Invoca el servicio para obtener el cliente
        ClienteDTO cliente = clienteService.obtenerCliente(tipoDocumento, numeroDocumento);
        return ResponseEntity.ok(cliente); // Retorna código 200 con el cliente encontrado
    }
}