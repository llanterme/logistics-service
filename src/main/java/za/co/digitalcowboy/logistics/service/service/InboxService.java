package za.co.digitalcowboy.logistics.service.service;

import za.co.digitalcowboy.logistics.service.domain.Event;
import za.co.digitalcowboy.logistics.service.entity.Inbox;

public interface InboxService {

    <D, C> Inbox writeEvent(Event<D, C> event);

    <D, C> void markAsRead(Event<D, C> event);
}
