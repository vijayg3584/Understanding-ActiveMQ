package au.com.unico.codingtest.jmsqueue.configuration;

import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JMSConfiguration {

	private static final String JMS_URL = "tcp://localhost:61616";

	private static final String NUMBER_QUEUE = "number-queue";

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(JMS_URL);
		connectionFactory.setTrustedPackages(Arrays.asList("au.com.unico.codingtest"));
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(NUMBER_QUEUE);
		return template;
	}

}
