package com.sistema.secundario.routes;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sistema.secundario.models.Cliente;

@Component
public class JmsRoute extends RouteBuilder {

	static final Logger log = LoggerFactory.getLogger(JmsRoute.class);
	
	@Override
	public void configure() throws Exception {
		
		JAXBContext con = JAXBContext.newInstance(Cliente.class);
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		xmlDataFormat.setContext(con);
		
		JaxbDataFormat xmlDataFormatSend = new JaxbDataFormat();
		JAXBContext context = JAXBContext.newInstance(Cliente.class);
		xmlDataFormatSend.setContext(context);

		
		from("{{inbound.endpoint}}")
		.transacted()
		.unmarshal(xmlDataFormat)
		.log(LoggingLevel.INFO, log, "Mensaje recibido - ${body}")
		.process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				Cliente cliente = exchange.getIn().getBody(Cliente.class);
				log.info(cliente.toString());
				cliente.setMonto(500.99);
				exchange.getIn().setBody(cliente);
			}
			
		})
		.marshal(xmlDataFormatSend)
		.to("{{outbound.endpoint}}")
		.log(LoggingLevel.INFO, log, "Mensaje Enviado. Iteración: ${body}")
		.end();
		
	}

}
