package com.nttdata.backend_nttdata_rsp.service;

import com.nttdata.backend_nttdata_rsp.model.dto.ClienteDTO;

public interface IClienteService {

    ClienteDTO obtenerCliente(String tipoDocumento, String numeroDocumento);

}