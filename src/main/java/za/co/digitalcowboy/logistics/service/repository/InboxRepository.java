package za.co.digitalcowboy.logistics.service.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.digitalcowboy.logistics.service.domain.MessageType;
import za.co.digitalcowboy.logistics.service.entity.Inbox;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InboxRepository extends CrudRepository<Inbox, Long> {

  Optional<Inbox> findByEventIdAndType(UUID eventId, MessageType type);
}
