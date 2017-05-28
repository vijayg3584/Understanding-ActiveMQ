package au.com.unico.codingtest.restservices;

import java.util.List;

public interface QueueService {
	
	void addToQueue(Integer number);

	List<Integer> getNumbersList();
}
