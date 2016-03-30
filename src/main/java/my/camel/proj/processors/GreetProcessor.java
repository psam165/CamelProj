package my.camel.proj.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetProcessor implements Processor {

	private static Logger logger = LoggerFactory.getLogger(GreetProcessor.class);
	
	public void process(Exchange exchange) throws Exception {
		
		logger.info("Processor's process is started");
		logger.info("Incoming exchange is"+exchange);
		exchange.getOut().setBody("<greetResponse xmlns=\"http://service.cxf.javax/\">Hello</greetResponse>");
		//exchange.getIn().setBody("<greetResponse xmlns=\"http://service.cxf.javax/\">Hello</greetResponse>");
		logger.info("Outgoing exchange is"+exchange);	
			
			
	}

}
