package com.emrpesa.sistema.legado.models.services;

import org.springframework.stereotype.Service;

import com.emrpesa.sistema.legado.models.Cliente;
import com.emrpesa.sistema.legado.utils.XmlJmsUtils;


@Service
public class ClienteServiceImpl implements IClienteService{

	@Override
	public String guardarClienteMensaje(Cliente cliente, String tipoProceso, Long id) {
		cliente.setTipoDeProceso(tipoProceso);
		String xmlEnviar = XmlJmsUtils.getXmlToSendJms(cliente, id);
		return xmlEnviar;
	}
}
