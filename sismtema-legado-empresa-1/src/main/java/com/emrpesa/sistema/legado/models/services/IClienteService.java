package com.emrpesa.sistema.legado.models.services;

import java.util.List;

import com.emrpesa.sistema.legado.models.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public String sendMessage(Long id);
}
