package za.co.digitalcowboy.logistics.service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.digitalcowboy.logistics.service.domain.Event;
import za.co.digitalcowboy.logistics.service.domain.MessageType;
import za.co.digitalcowboy.logistics.service.entity.Inbox;
import za.co.digitalcowboy.logistics.service.exception.DomainException;
import za.co.digitalcowboy.logistics.service.exception.ErrorCode;
import za.co.digitalcowboy.logistics.service.repository.InboxRepository;
import za.co.digitalcowboy.logistics.service.service.InboxService;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;


@Service
@Log4j2
public class InboxServiceImpl implements InboxService {

    @Autowired
    private InboxRepository inboxRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public <D, C> Inbox writeEvent(Event<D, C> event) {
        return findEvent(event.getId(), event.getType())
                .orElseGet(() -> writeToInbox(event));
    }

    @Override
    public <D, C> void markAsRead(Event<D, C> event) {
        var inbox = findEvent(event.getId(), event.getType())
                .orElseThrow(() -> new DomainException(ErrorCode.EVENT_NOT_FOUND));
        if (inbox.isRead()) {
            throw new DomainException(ErrorCode.EVENT_ALREADY_MARKED_AS_READ);
        }
        inbox.setRead(true);
        inbox.setNote(StringUtils.EMPTY);
        inboxRepository.save(inbox);
    }

    private Optional<Inbox> findEvent(String eventId, MessageType type) {
        var eventUUID = UUID.fromString(eventId);
        return inboxRepository.findByEventIdAndType(eventUUID, type);
    }

    private <D, C> Inbox writeToInbox(Event<D, C> event) {
        var eventId = UUID.fromString(event.getId());
        var eventTime = event.getTime()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();
        try {
            var inbox = Inbox.builder()
                    .eventId(eventId)
                    .eventTime(eventTime)
                    .isRead(false)
                    .payload(objectMapper.writeValueAsString(event))
                    .note(null)
                    .type(event.getType())
                    .build();
            return inboxRepository.save(inbox);
        } catch (JsonProcessingException ex) {
            log.error("error occurred on json serialize", ex);
            return null;
        }
    }


}
