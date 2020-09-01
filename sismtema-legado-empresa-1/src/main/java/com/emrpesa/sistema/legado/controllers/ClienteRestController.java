package com.emrpesa.sistema.legado.controllers;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emrpesa.sistema.legado.models.Cliente;
import com.emrpesa.sistema.legado.models.services.IClienteService;

@RestController
@RequestMapping("/empresa")
public class ClienteRestController {
	
	static final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	Queue queue;
	
	private String tipoProceso;
	private String mensajeEnviar;
	
	@PostMapping("/cliente-nuevo")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crearCliente(@RequestBody Cliente cliente) {
		tipoProceso = "guardar";
		mensajeEnviar = clienteService.guardarClienteMensaje(cliente, tipoProceso, null);
		log.info("MENSAJE --> " + mensajeEnviar);
		jmsTemplate.convertAndSend(queue, mensajeEnviar);
		return null;
	}
	
	@PutMapping("/actualizar-cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente actualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
		tipoProceso = "actualizar";
		mensajeEnviar = clienteService.guardarClienteMensaje(cliente, tipoProceso, id);
		log.info("MENSAJE --> " + mensajeEnviar);
		jmsTemplate.convertAndSend(queue, mensajeEnviar);
		return null;
	}
	
	@DeleteMapping("/eliminar-cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		tipoProceso = "eliminar";
		Cliente cliente = new Cliente();
		mensajeEnviar = clienteService.guardarClienteMensaje(cliente, tipoProceso, id);
		log.info("MENSAJE --> " + mensajeEnviar);
		jmsTemplate.convertAndSend(queue, mensajeEnviar);
	}
	
}
