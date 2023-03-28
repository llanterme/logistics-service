package za.co.digitalcowboy.logistics.service.service;

import za.co.digitalcowboy.logistics.service.domain.Event;
import za.co.digitalcowboy.logistics.service.domain.Order;
import za.co.digitalcowboy.logistics.service.domain.OrderContext;

public interface OrderService {

    void processOrder(Event<Order, OrderContext> event);

}
