package za.co.digitalcowboy.logistics.service.service.impl;


import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import za.co.digitalcowboy.logistics.service.service.SqsMessageProducer;

@Component
@Log4j2
public class SqsMessageProducerImpl implements SqsMessageProducer {

  @Autowired
  private QueueMessagingTemplate queueMessagingTemplate;

  @Value("${cloud.aws.sqs.logistics-process-queue-name}")
  private String paymentProcessQueueName;

  @Override
  public void sendToOrderProcessQueue(String message) {
    queueMessagingTemplate.convertAndSend(paymentProcessQueueName, message);
  }
}
