package my.camel.proj.endpoint;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@WebService
@BindingType(value=javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)

//@HandlerChain(file="/WEB-INF/jaxws-handler.xml")
public class GreetingService {
	
private static Logger log4j = LoggerFactory.getLogger(GreetingService.class);
	
	@WebMethod
	@SOAPBinding
	public String greet(){
		log4j.info("Just checking......log4j");
		return "Hello World";
	}

	
	
}
