package com.sistema.receptor.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.sistema.receptor.models.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
