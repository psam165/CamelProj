package my.camel.proj.processors;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAggregator implements AggregationStrategy {
	private static Logger logger = LoggerFactory.getLogger(SimpleAggregator.class);

	public Exchange aggregate(Exchange oldE, Exchange newE) {
		if (null == oldE)
			return newE;
		String oldBody = oldE.getIn().getBody(String.class);
		String newBody = newE.getIn().getBody(String.class);
		logger.info("Old body : {}", oldBody);
		logger.info("new body : {}", newBody);
		// String name =
		// XPathBuilder.xpath("/ns2:getDateResponse/ns2:return").evaluate(context,
		// "<foo><bar>cheese</bar></foo>", String.class);
		StringBuilder output = new StringBuilder(
				"<ns2:greetResponse xmlns:ns2=\"http://service.cxf.javax/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><return>")
						.append(newBody).append(" ").append(oldBody).append("</return></ns2:greetResponse>");
		oldE.getIn().setBody(output);
		logger.info("aggregated body" + output);
		return oldE;
	}

}
