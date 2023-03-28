package za.co.digitalcowboy.logistics.service.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.digitalcowboy.logistics.service.domain.Event;
import za.co.digitalcowboy.logistics.service.service.InboxService;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public abstract class AbstractInboxConsumer<D, C> {
  private static final int MAXIMUM_RECEIVES  = 10;

  private static final Map<Integer, Integer> RETRY_DURATION_MAP = IntStream
      .range(1, MAXIMUM_RECEIVES)
      .boxed()
      .collect(Collectors.toMap(
          Function.identity(),
          i -> ((Double) Math.pow(2, (double) i - 1)).intValue())
      );

  protected IntUnaryOperator nextRetryCalculator =
      count -> RETRY_DURATION_MAP.getOrDefault(count, 0);

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  private InboxService inboxService;

  protected abstract void consume(String message);

  protected abstract void process(Event<D,C> event);

  protected void consume(String message, JavaType eventType) {
    var event = deserialize(message, eventType);

    var inbox = inboxService.writeEvent(event);
    if (!inbox.isRead()) {
      process(event);
    }
  }

  private Event<D, C> deserialize(String rawPayload, JavaType javaType) {
    try {
      return objectMapper.readValue(rawPayload, javaType);
    } catch (JsonProcessingException ex) {
      throw new RuntimeException("Cannot deserialize the message", ex);
    }
  }




}
