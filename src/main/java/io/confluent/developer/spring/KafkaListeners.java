package io.confluent.developer.spring;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "evistopic", groupId = "foo")
    public void listen(String data){
        System.out.println("Received " + data + " 😎");
    }
}
