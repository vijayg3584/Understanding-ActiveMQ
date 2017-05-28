package au.com.unico.codingtest.restservices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.unico.codingtest.common.GcdService;

@RestController
@RequestMapping(path = "/restapi/numbers")
public class QueueController {

	private static final Logger logger = LoggerFactory.getLogger(QueueController.class);

	@Autowired
	private QueueService queueService;
	
	@Autowired
	private GcdService gcdService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String push(@RequestParam("i1") int i1, @RequestParam("i2") int i2) {
		logger.debug("Request parameters i1:" + i1 + " and i2:" + i2);
		
		StringBuilder result = new StringBuilder();
		try {
			queueService.addToQueue(i1);
			result.append(i1 + " successfully added to the queue. ");
		} catch (Exception exception) // intentionally catching all exceptions										// here
		{
			String message = "Error adding " + i1 + " to the queue. ";
			result.append(message);
			logger.error(message, exception);
		}

		try {
			queueService.addToQueue(i2);
			result.append(i2 + " successfully added to the queue. ");
		} catch (Exception exception) // intentionally catching all exceptions
										// here
		{
			String message = "Error adding " + i2 + " to the queue. ";
			result.append(message);
			logger.error(message, exception);
		}
		
		try {
			gcdService.persistNumbersAlongWithGcd(i1, i2);
		} catch (Exception e) {
			logger.error("Error while persisting numnbers and their gcd.", e);
		}
		return result.toString();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Integer> list()
	{
		return queueService.getNumbersList();
	}
}
