package com.emrpesa.sistema.legado.controllers;

import java.util.List;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emrpesa.sistema.legado.models.Cliente;
import com.emrpesa.sistema.legado.models.services.IClienteService;

@RestController
@RequestMapping("/empresa")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	Queue queue;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente mostrarClientePorId(@PathVariable Long id) {
		String mensajeEnviar = clienteService.sendMessage(id);
		System.out.println("MENSAJE --> " + mensajeEnviar);
		jmsTemplate.convertAndSend(queue, mensajeEnviar);
		return clienteService.findById(id);
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crearCliente(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
}
