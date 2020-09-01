package com.emrpesa.sistema.legado.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emrpesa.sistema.legado.models.Cliente;

public class XmlJmsUtils {
	static final Logger log = LoggerFactory.getLogger(XmlJmsUtils.class);

	public static final String getXmlToSendJms(Cliente cliente, Long id) {
		
		log.info("FEHCA DE VENTA -> " + cliente.getFechaVenta());
		
		StringBuilder sb = new StringBuilder();
		sb.append("<cliente>").append("\n");
		sb.append("		<id>").append(id).append("</id>").append("\n");
		sb.append("		<fechaVenta>").append(cliente.getFechaVenta()).append("</fechaVenta>").append("\n");
		sb.append("		<monto>").append(cliente.getMonto()).append("</monto>").append("\n");
		sb.append("		<sucursal>").append(cliente.getSucursal()).append("</sucursal>").append("\n");
		sb.append("		<idCliente>").append(cliente.getIdCliente()).append("</idCliente>").append("\n");
		sb.append("		<canal>").append(cliente.getCanal()).append("</canal>").append("\n");
		sb.append("		<tipoDeProceso>").append(cliente.getTipoDeProceso()).append("</tipoDeProceso>").append("\n");
		sb.append("</cliente>").append("\n");
		return sb.toString();
	}
	
}
