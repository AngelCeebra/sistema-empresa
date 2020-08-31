package com.emrpesa.sistema.legado.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emrpesa.sistema.legado.models.Cliente;
import com.emrpesa.sistema.legado.models.dao.IClienteDao;
import com.emrpesa.sistema.legado.utils.XmlJmsUtils;


@Service
public class ClienteServiceImpl implements IClienteService{

	private final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}
	
	public String sendMessage(Long id) {
		String xmlEnviar = XmlJmsUtils.getXmlToSendJms(id);
		logger.info("XML A ENVIAR " + xmlEnviar);
		return xmlEnviar;
	}
	
	public Boolean sendJMS(final String destination, final String text) {
		
		return true;
	}
}
