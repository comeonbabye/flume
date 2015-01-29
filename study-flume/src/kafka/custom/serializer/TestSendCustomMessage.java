package kafka.custom.serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class TestSendCustomMessage {

	
	public static void send() {
		Properties props = new Properties();
		props.put("zk.connect", "192.168.1.215:2181");
		props.put("metadata.broker.list", "dev.com:9092");
		/**选择用哪个类来进行序列化*/
		props.put("serializer.class", "kafka.custom.serializer.CustomEncoder");
		props.put("zk.connectiontimeout.ms", "6000");
		ProducerConfig config=new ProducerConfig(props);
		
		/**制造数据*/
		Item item = new Item();
		item.setId("00000003");
		item.setName("test3");
		
		List<Item> msg = new ArrayList<Item>();
		msg.add(item);
		
		/**构造数据发送对象*/
		Producer<String, Item> producer = new Producer<String, Item>(config);		
		KeyedMessage<String,Item> data = new KeyedMessage<String, Item>("test2", item);
		producer.send(data);
	}
	
	/**
	 * 功能:
	 * 作者: tony.he
	 * 创建日期:2015-1-21
	 * 修改者: mender
	 * 修改日期: modifydate
	 * @param args
	 */
	public static void main(String[] args) {
		send();
	}

}
