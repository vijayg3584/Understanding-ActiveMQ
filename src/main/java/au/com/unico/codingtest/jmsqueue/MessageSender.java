package au.com.unico.codingtest.jmsqueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.unico.codingtest.jmsqueue.configuration.JMSConfiguration;

@Component
public class MessageSender {

	@Autowired
	private JMSConfiguration jmsConfiguration;
	
	public void sendMessage(Integer number)
	{
//		jmsConfiguration.jmsTemplate().send(new MessageCreator() {
//			
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				return session.createObjectMessage(number);
//			}
//		});
		jmsConfiguration.jmsTemplate().convertAndSend(number.toString());
	}
}
