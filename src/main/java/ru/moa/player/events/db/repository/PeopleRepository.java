package ru.moa.player.events.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.moa.player.events.db.entity.PersonEntity;

public interface PeopleRepository extends JpaRepository<PersonEntity, Long> {
}
