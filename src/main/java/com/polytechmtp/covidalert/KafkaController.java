package com.polytechmtp.covidalert;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final com.polytechmtp.covidalert.KafkaProducer producer;

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestParam("message") String message){
        this.producer.writeMessage(message);

    }

}