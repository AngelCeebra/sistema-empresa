package com.emrpesa.sistema.legado.utils;

public class XmlJmsUtils {

	public static final String getXmlToSendJms(Long id) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF8\" ?>").append("\n");
		sb.append("	<cliente>").append("\n");
		sb.append("		<id>").append(id).append("</id>").append("\n");
		sb.append("	</cliente>").append("\n");
		return sb.toString();
	}
	
}
