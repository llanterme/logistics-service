package za.co.digitalcowboy.logistics.service.messaging;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import za.co.digitalcowboy.logistics.service.service.SqsMessageProducer;

import java.util.function.Consumer;

@Slf4j
@Component("order-consumer")
public class OrderKafkaConsumer implements Consumer<Message<String>>  {

    @Autowired
    private SqsMessageProducer sqsMessageProducer;

    @Override
    public void accept(Message<String> message) {
        try {
            log.info("Incoming kafka event received : {}", message.getPayload());
            var payload = message.getPayload();
            sqsMessageProducer.sendToOrderProcessQueue(payload);
            ack(message);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    private void ack(Message<String> message) {
        var acknowledgment = message.getHeaders()
                .get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            acknowledgment.acknowledge();
        }
    }



}
