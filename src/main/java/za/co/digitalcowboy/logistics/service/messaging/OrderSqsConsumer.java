package za.co.digitalcowboy.logistics.service.messaging;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.digitalcowboy.logistics.service.domain.Event;
import za.co.digitalcowboy.logistics.service.domain.Order;
import za.co.digitalcowboy.logistics.service.domain.OrderContext;
import za.co.digitalcowboy.logistics.service.service.OrderService;

@Component
@Log4j2
public class OrderSqsConsumer extends AbstractInboxConsumer<Order, OrderContext> {

  @Autowired
  private OrderService orderService;


  private final JavaType accountType = TypeFactory.defaultInstance().constructParametricType(
      Event.class, Order.class, OrderContext.class);

  @SqsListener(value = "${cloud.aws.sqs.logistics-process-queue-name}", deletionPolicy = SqsMessageDeletionPolicy.DEFAULT)
  public void consume(String message) {
    consume(message, accountType);
  }

  @Override
  protected void process(Event<Order, OrderContext> event) {
    orderService.processOrder(event);


    }
  }

