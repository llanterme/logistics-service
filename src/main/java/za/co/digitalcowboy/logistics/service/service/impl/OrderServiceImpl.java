package za.co.digitalcowboy.logistics.service.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.digitalcowboy.logistics.service.domain.Event;
import za.co.digitalcowboy.logistics.service.domain.Order;
import za.co.digitalcowboy.logistics.service.domain.OrderContext;
import za.co.digitalcowboy.logistics.service.service.InboxService;
import za.co.digitalcowboy.logistics.service.service.OrderService;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    InboxService inboxService;

    @Override
    public void processOrder(Event<Order, OrderContext> event) {

        log.info("Processing new order : {}", event.getDetail());
        inboxService.markAsRead(event);
    }
}
