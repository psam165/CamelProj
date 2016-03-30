package my.camel.proj.route.java;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.camel.proj.processors.SimpleAggregator;

public class HelloWorldRouteBuilder extends RouteBuilder {

	private static Logger logger = LoggerFactory.getLogger(HelloWorldRouteBuilder.class);

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stubl

		from("cxf:bean:greetEndpoint").setHeader("myId",constant("123")).to("direct://greetRoute");
		/*
		 * from("direct://greetRoute").process(new Processor(){
		 * 
		 * public void process(Exchange exchange) throws Exception { // TODO
		 * Auto-generated method stub logger.info("Exchange is {}", exchange);
		 * exchange.getOut().setBody(
		 * "<greetResponse xmlns=\"http://service.cxf.javax/\">Hello from Java DSL route</greetResponse>"
		 * ); }
		 * 
		 * });
		 */
		from("direct://greetRoute").multicast(new SimpleAggregator()).parallelProcessing().to("direct:calRoute",
				"direct:greetClientRoute");
		//from("direct:agr").aggregate(header("myId"), new SimpleAggregator()).completionSize(2).log(LoggingLevel.DEBUG, "Processing ${id}");
		
		from("direct:calRoute")
				.setHeader("operationNamespace",
						constant("http://service.cxf.javax/"))
				.setHeader("operationName",
						constant("getDate")).process(new Processor(){

							public void process(Exchange exch) throws Exception {
								
								exch.getIn().setBody(" <ser:getDate xmlns:ser=\"http://service.cxf.javax/\"/>");
								
							}
							
						})
				.to("cxf:bean:calClient");
		from("direct:greetClientRoute")
		/*.setHeader("operationNamespace",
				simple("${type:org.apache.camel.component.cxf.common.message.CxfConstants.DISPATCH_NAMESPACE}"))
		.setHeader("operationName",
				simple("${type:org.apache.camel.component.cxf.common.message.CxfConstants.DISPATCH_DEFAULT_OPERATION_NAMESPACE}")).to("log:foo?showHeaders=true")
		*/.to("cxf:bean:greetClient");
	}

}
