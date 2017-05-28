package au.com.unico.codingtest.restservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.unico.codingtest.derbyDB.numbers.NumberRepository;
import au.com.unico.codingtest.jmsqueue.MessageSender;

@Service
public class QueueServiceImpl implements QueueService {
	
	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private NumberRepository repository;
	
	@Override
	public void addToQueue(Integer number) {
		//then add to the JMS Queue
		messageSender.sendMessage(number);
	}

	@Override
	public List<Integer> getNumbersList() {
		return repository.getNumberInOrderOfInsert();
	}

}
