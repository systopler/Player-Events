package ru.moa.player.events.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.moa.player.events.db.entity.OrganizationTypeEntity;

public interface OrganizationTypeRepository extends JpaRepository<OrganizationTypeEntity, Long> {
}
