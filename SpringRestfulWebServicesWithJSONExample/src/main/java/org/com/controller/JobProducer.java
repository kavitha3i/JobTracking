package org.com.controller;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord; 
public class JobProducer {
	
	public static String topicName = "topictests";
	static String key = "keytest";
	//static String value="valeustest";
	
	public void kafkamethod(String jobDetails){
		try{
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> sampleProduce = new KafkaProducer<String,String>(props);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName,key,jobDetails);
		sampleProduce.send(record);
		sampleProduce.close();
		System.out.println("record update successfully");
		}catch(Exception e){
			System.out.println("error occured");
		}finally {
			}
		}

}
