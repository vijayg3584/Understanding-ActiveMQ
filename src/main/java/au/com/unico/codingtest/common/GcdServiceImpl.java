package au.com.unico.codingtest.common;

import static au.com.unico.codingtest.common.Util.getGcd;

import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.unico.codingtest.derbyDB.gcd.GcdNumberRespository;
import au.com.unico.codingtest.derbyDB.gcd.GcdNumbers;
import au.com.unico.codingtest.derbyDB.numbers.NumberQueue;
import au.com.unico.codingtest.derbyDB.numbers.NumberRepository;
import au.com.unico.codingtest.jmsqueue.MessageReader;
import au.com.unico.codingtest.jmsqueue.NoMessageException;

@Service
@Transactional
public class GcdServiceImpl implements GcdService {
	
	@Autowired
	private NumberRepository numberRepository;
	
	@Autowired
	private GcdNumberRespository gcdRepository;
	
	@Autowired
	private MessageReader reader;

	@Override
	public void persistNumbersAlongWithGcd(Integer i1, Integer i2) {
		//persist in database first
		long sequence = new Date().getTime();
		
		persistNumber(i1, sequence);
		persistNumber(i2, sequence);
		
		persistGcd(sequence, getGcd(i1, i2));
	}

	@Override
	public List<Integer> getGcdNumbersList() {
		return gcdRepository.getAllGcdNumbers();
	}

	@Override
	public Integer getSumOfGcdNumbers() {
		return gcdRepository.getSumOfGcdNumbers();
	}

	@Override
	public Integer getGcdOfNumbersAtHeadOfQueue() throws JMSException, NoMessageException {
		
		Integer i1 = reader.getAndDeleteFromQueue();
		Integer i2 = reader.getAndDeleteFromQueue();
		
		return getGcd(i1, i2);
	}
	
	private void persistNumber(Integer number, long sequence) {
		NumberQueue numberQueue = new NumberQueue();
		numberQueue.setNumber(number);
		numberQueue.setInsertedDate(new Date());
		numberQueue.setSequence(sequence);
		numberRepository.save(numberQueue);
	}
	
	private void persistGcd(long sequence, int gcd){
		GcdNumbers gcdNumbers = new GcdNumbers();
		gcdNumbers.setId(sequence);
		gcdNumbers.setGcd(gcd);
		gcdRepository.save(gcdNumbers);
	}
}
