package com.emrpesa.sistema.legado.models.services;

import com.emrpesa.sistema.legado.models.Cliente;

public interface IClienteService {
	
	public String guardarClienteMensaje(Cliente cliente, String tipoProceso, Long id);
}
