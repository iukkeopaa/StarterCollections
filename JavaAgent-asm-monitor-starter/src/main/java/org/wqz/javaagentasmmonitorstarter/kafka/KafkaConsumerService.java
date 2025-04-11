package org.wqz.javaagentasmmonitorstarter.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "${monitor.kafka.topic}", groupId = "monitor-group")
    public void listen(String message) {
        try (FileWriter writer = new FileWriter("monitor.log", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}    