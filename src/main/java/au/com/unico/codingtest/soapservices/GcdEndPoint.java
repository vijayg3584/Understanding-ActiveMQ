package au.com.unico.codingtest.soapservices;

import static au.com.unico.codingtest.soapservices.config.SoapServiceConfig.NAMESPACE_URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import au.com.unico.codingtest.common.GcdService;
import au.com.unico.codingtest.jmsqueue.NoMessageException;
import au.com.unico.codingtest.soapservices.gcd.Gcd;
import au.com.unico.codingtest.soapservices.gcd.GcdList;
import au.com.unico.codingtest.soapservices.gcd.GcdListExtn;
import au.com.unico.codingtest.soapservices.gcd.GcdSum;

@Endpoint
public class GcdEndPoint{
	private static final Logger logger = LoggerFactory.getLogger(GcdEndPoint.class); 
	
	
	@Autowired
	private GcdService service;

	@PayloadRoot(namespace=NAMESPACE_URI, localPart="gcd")
	@ResponsePayload
	public Gcd gcd(@RequestPayload Gcd request){
		Gcd response = new Gcd();
		try {
			response.setGcdValue(service.getGcdOfNumbersAtHeadOfQueue());
		} catch (NoMessageException e) {
			// TODO Auto-generated catch block
			logger.error("No number present in queue",e);
			throw new RuntimeException("No number present in queue or only one number present. Please try after inserting a few.");
		}catch (Exception e) {
			logger.error("Error while getting gcd of first two numbers from queue.",e);
			throw new RuntimeException("Unable to get the gcd of first two numbers from queue.");
		}
		return response;
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="gcdList")
	@ResponsePayload
	public GcdList gcdList(@RequestPayload GcdList request){
		GcdListExtn response = new GcdListExtn();
		response.setGcdList(service.getGcdNumbersList());
		return response;
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="gcdSum")
	@ResponsePayload
	public GcdSum gcdSum(@RequestPayload GcdSum request)
	{
		GcdSum response = new GcdSum();
		response.setGcdSumValue(service.getSumOfGcdNumbers());
		return response;
	}
	
}
