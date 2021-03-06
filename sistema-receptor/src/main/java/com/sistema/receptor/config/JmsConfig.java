package com.sistema.receptor.config;

import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.JmsTransactionManager;

public class JmsConfig {

	@Bean
	public JmsTransactionManager createJmsTransactionManager(final ConnectionFactory connectionFactory) {
		JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
		jmsTransactionManager.setConnectionFactory(connectionFactory);
		return jmsTransactionManager;
	}
	
	@Bean
	public JmsComponent createJmsComponent(final ConnectionFactory connectionFactory, @Value("${max.concurrent.consumers}") final int maxConcurrentConsumers, final JmsTransactionManager jmsTransactionManager) {
		JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory, jmsTransactionManager);
		jmsComponent.setMaxConcurrentConsumers(maxConcurrentConsumers);
		return jmsComponent;
	}
	
}
