package com.polytechmtp.covidalert;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Service
public class KafkaConsumer  {

    @KafkaListener(topics="my_topic", groupId="my_group_id")
    public void getMessage(String message){

        System.out.println(message);
        try {
            Files.write(Paths.get("logs/kafka/position.txt"), (message + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println(e);
        }

    }



}