package com.emrpesa.sistema.legado.utils;

public class XmlJmsUtils {

	public static final String getXmlToSendJms(Long id) {
		StringBuilder sb = new StringBuilder();
		sb.append("<cliente>").append("\n");
		sb.append("		<id>").append(id).append("</id>").append("\n");
		sb.append("		<fechaVenta>30-08-20</fechaVenta>").append("\n");
		sb.append("		<monto>30.5</monto>").append("\n");
		sb.append("		<sucursal>432</sucursal>").append("\n");
		sb.append("		<idCliente>AAA456</idCliente>").append("\n");
		sb.append("		<canal>20</canal>").append("\n");
		sb.append("</cliente>").append("\n");
		return sb.toString();
	}
	
}
