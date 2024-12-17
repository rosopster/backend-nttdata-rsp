package com.nttdata.backend_nttdata_rsp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nttdata.backend_nttdata_rsp.common.exception.BadRequestException;
import com.nttdata.backend_nttdata_rsp.common.exception.ClienteNotFoundException;
import com.nttdata.backend_nttdata_rsp.common.util.MessageUtil;
import com.nttdata.backend_nttdata_rsp.model.dto.ClienteDTO;
import com.nttdata.backend_nttdata_rsp.model.entity.Cliente;

@Service
public class ClienteService implements IClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final MessageUtil messageUtil;

    // Datos quemados en memoria
    private static final List<Cliente> clientes = List.of(
            new Cliente("C", "23445322", "Jhenny", "Maria", "Pérez", "Gómez", "831297", "Calle del foquito Puerta cafe", "Macondo")
    );

    ClienteService(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }

    @Override
    public ClienteDTO obtenerCliente(String tipoDocumento, String numeroDocumento) {
        logger.info("Buscando cliente con tipoDocumento={} y numeroDocumento={}", tipoDocumento, numeroDocumento);

        // Validación de tipo de documento
        if (!"C".equals(tipoDocumento) && !"P".equals(tipoDocumento)) {
            logger.error("Tipo de documento no válido: {}", tipoDocumento);
            throw new BadRequestException(messageUtil.getMessage("error.tipo_documento_invalido"));
        }

        // Validación de parámetros
        if (tipoDocumento.isEmpty() || numeroDocumento.isEmpty()) {
            logger.error("Parámetros faltantes: tipoDocumento={} numeroDocumento={}", tipoDocumento, numeroDocumento);
            throw new BadRequestException(messageUtil.getMessage("error.parametros_obligatorios"));
        }

        // Búsqueda del cliente en la lista
        return clientes.stream()
        .filter(c -> c.getTipoDocumento().equals(tipoDocumento) && c.getNumeroDocumento().equals(numeroDocumento))
        .findFirst()
        .map(cliente -> new ClienteDTO(
                cliente.getPrimerNombre(),
                cliente.getSegundoNombre(),
                cliente.getPrimerApellido(),
                cliente.getSegundoApellido(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getCiudadResidencia()
        ))
        .orElseThrow(() -> {
            logger.warn("Cliente no encontrado para tipoDocumento={} y numeroDocumento={}", tipoDocumento, numeroDocumento);
            return new ClienteNotFoundException(messageUtil.getMessage("error.cliente_no_encontrado"));
        });
    }
}
