package au.com.unico.codingtest.soapservices.config;

import java.util.List;
import java.util.Properties;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapServiceConfig extends WsConfigurerAdapter {
	
	public static final String NAMESPACE_URI = "http://www.unico.com.au/codingtest/soapservices/gcd";
	
	static{
		org.apache.xml.security.Init.init();
	}
	
	@Bean
	public SimplePasswordValidationCallbackHandler securityCallBackHandler()
	{
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		Properties users = new Properties();
		users.setProperty("unico-soap-user", "a1a2a3a4");
		handler.setUsers(users);
		return handler;
	}
	
	@Bean
	public Wss4jSecurityInterceptor securityInterceptor()
	{
		Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
		interceptor.setValidationActions("Timestamp UsernameToken");
		interceptor.setValidationCallbackHandler(securityCallBackHandler());
		return interceptor;
	}
	
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context)
	{
		MessageDispatcherServlet dispatcherServlet = new MessageDispatcherServlet();
		dispatcherServlet.setApplicationContext(context);
		dispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(dispatcherServlet, "/gcd/*");
	}
	
	@Bean(name = "unico-gcd")
	public DefaultWsdl11Definition getDefaultWsdl11Definition(XsdSchema schema)
	{
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("gcdPort");
		definition.setLocationUri("/gcd");
		definition.setTargetNamespace(NAMESPACE_URI);
		definition.setSchema(schema);
		return definition;
	}
	
	@Bean
	public XsdSchema schema(){
		return new SimpleXsdSchema(new ClassPathResource("unico-gcd.xsd"));
	}
}
