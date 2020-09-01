package com.sistema.receptor.models.services;

import java.util.List;

import com.sistema.receptor.models.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);

}
