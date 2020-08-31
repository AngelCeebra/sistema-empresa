package com.sistema.secundario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.secundario.models.Cliente;
import com.sistema.secundario.services.IClienteService;

@RestController
public class ClienteController {

	@Autowired
	IClienteService clienteService;
	
	@GetMapping("/mensaje-clientes")
	public List<Cliente> recibeMensajeClientes() {
		clienteService.recibirMensaje();
		return null;
	}
	
}
