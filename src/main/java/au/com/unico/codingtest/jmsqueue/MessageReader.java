package au.com.unico.codingtest.jmsqueue;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.unico.codingtest.jmsqueue.configuration.JMSConfiguration;

@Component
public class MessageReader {

	@Autowired
	private JMSConfiguration jmsConfiguration;
	
	public Integer getAndDeleteFromQueue() throws JMSException, NoMessageException
	{
		jmsConfiguration.jmsTemplate().setReceiveTimeout(1000);
		String text = (String) jmsConfiguration.jmsTemplate().receiveAndConvert();
		if(text==null){
			throw new NoMessageException();
		}
		return Integer.valueOf((String)text);
	}
}
