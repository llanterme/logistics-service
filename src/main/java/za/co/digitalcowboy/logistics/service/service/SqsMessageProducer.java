package za.co.digitalcowboy.logistics.service.service;

public interface SqsMessageProducer {

  void sendToOrderProcessQueue(String message);

}
