package com.sistema.receptor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.receptor.models.Cliente;
import com.sistema.receptor.models.services.IClienteService;

@RestController
@RequestMapping("/receptor")
public class ClienteController {

	@Autowired
	IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> clientes(){
		return clienteService.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente mostrarClientePorId(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crear(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@PutMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente actualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		
		clienteActual.setCanal(cliente.getCanal());
		clienteActual.setFechaVenta(cliente.getFechaVenta());
		clienteActual.setIdCliente(cliente.getIdCliente());
		clienteActual.setMonto(cliente.getMonto());
		clienteActual.setSucursal(cliente.getSucursal());
		clienteActual.setTipoDeProceso(cliente.getTipoDeProceso());
		
		return clienteService.save(clienteActual);
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
}

