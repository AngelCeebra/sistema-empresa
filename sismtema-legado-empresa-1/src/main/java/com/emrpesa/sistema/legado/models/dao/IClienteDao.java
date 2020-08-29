package com.emrpesa.sistema.legado.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.emrpesa.sistema.legado.models.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
