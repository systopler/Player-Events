package ru.moa.player.events.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.moa.player.events.db.entity.contact.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
