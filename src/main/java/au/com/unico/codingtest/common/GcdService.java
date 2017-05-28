package au.com.unico.codingtest.common;

import java.util.List;

import javax.jms.JMSException;

import au.com.unico.codingtest.jmsqueue.NoMessageException;

public interface GcdService {
	
	void persistNumbersAlongWithGcd(Integer i1, Integer i2);

	List<Integer> getGcdNumbersList();
	
	Integer getSumOfGcdNumbers();
	
	Integer getGcdOfNumbersAtHeadOfQueue() throws JMSException, NoMessageException;

}
