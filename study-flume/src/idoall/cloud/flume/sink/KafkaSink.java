package idoall.cloud.flume.sink;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaSink extends AbstractSink implements Configurable {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSink.class);
	
	private String topic;
    private Producer<String, String> producer;
    
	@Override
	public void configure(Context arg0) {
		
		topic = "3test";
        Properties props = new Properties();
        props.setProperty("metadata.broker.list", "192.168.1.216:9092,192.168.1.216:9093,192.168.1.216:9094");
        props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "idoall.cloud.flume.sink.Partitionertest");
        props.put("zookeeper.connect", "192.168.1.215:2181");
        props.setProperty("num.partitions", "0"); //
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<String, String>(config);
        LOGGER.info("KafkaSink初始化完成.");
        
		
	}

	@Override
	public Status process() throws EventDeliveryException {
		Channel channel = getChannel();
        Transaction tx = channel.getTransaction();
        try {
            tx.begin();
            Event e = channel.take();
            if (e == null) {
                tx.rollback();
                return Status.BACKOFF;
            }
            KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, new String(e.getBody()));
            producer.send(data);
            LOGGER.info("flume向kafka发送消息：" + new String(e.getBody()));
            tx.commit();
            return Status.READY;
        } catch (Exception e) {
        	LOGGER.error("Flume KafkaSinkException:", e);
            tx.rollback();
            return Status.BACKOFF;
        } finally {
            tx.close();
        }
	}

}
