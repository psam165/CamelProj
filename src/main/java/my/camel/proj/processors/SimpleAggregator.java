package my.camel.proj.processors;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAggregator  implements AggregationStrategy {
	private static Logger logger = LoggerFactory.getLogger(SimpleAggregator.class);
	public Exchange aggregate(Exchange oldE, Exchange newE) {
		if(null==oldE) return newE;
		String oldBody = oldE.getIn().getBody(String.class);
		String newBody = newE.getIn().getBody(String.class);
		logger.info("Old body : {}", oldBody);
		logger.info("new body : {}", newBody);
		oldE.getIn().setBody(oldBody+newBody);
		logger.info("aggregated body"+oldE.getIn().getBody());
		return oldE;
	}

}
