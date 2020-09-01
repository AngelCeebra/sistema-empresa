package com.sistema.receptor.routes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistema.receptor.models.Cliente;
import com.sistema.receptor.models.services.IClienteService;

@Component
public class JmsRoute extends RouteBuilder {

	static final Logger log = LoggerFactory.getLogger(JmsRoute.class);
	
	@Autowired
	private IClienteService clienteService;
	
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
				switch(cliente.getTipoDeProceso()) {
					case "guardar":
						if(cliente.getFechaVenta().equals("null")) {
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							cliente.setFechaVenta(format.format(new Date()));
						}
						log.info("CLIENTE -> " + cliente.toString());
						clienteService.save(cliente);
						break;	
					case "actualizar":
						Cliente clienteActual = clienteService.findById(cliente.getId());
						
						clienteActual.setCanal(cliente.getCanal());
						clienteActual.setFechaVenta(cliente.getFechaVenta());
						clienteActual.setIdCliente(cliente.getIdCliente());
						clienteActual.setMonto(cliente.getMonto());
						clienteActual.setSucursal(cliente.getSucursal());
						
						clienteService.save(clienteActual);
						break;
					case "eliminar":
						Cliente clienteEliminar = clienteService.findById(cliente.getId());
						clienteService.delete(clienteEliminar.getId());
						break;
				}
				if(cliente.getTipoDeProceso().equals("guardar")) {
				}
				log.info(cliente.toString());
				exchange.getIn().setBody(cliente);
			}
			
		})
		.marshal(xmlDataFormatSend)
		.to("{{outbound.endpoint}}")
		.log(LoggingLevel.INFO, log, "Mensaje Enviado. Iteración: ${body}")
		.end();
		
	}

}
