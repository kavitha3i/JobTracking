package org.com.controller;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class JobConsumer {
	public void kafkaConsume(){
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id","test");
		props.put("enable.auto.commit", "true");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("topictests"));
	
		
		try{
		while (true) {
		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
		for(ConsumerRecord<String, String> record : records){
			String[] jobValues = (record.toString()).split(",");
			System.out.println(jobValues.toString());
		}
		}
		}catch(Exception e){
		System.out.println("error occured");
		}finally {
		consumer.close();
		}
		}

		

}
