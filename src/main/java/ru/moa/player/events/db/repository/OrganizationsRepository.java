package ru.moa.player.events.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.moa.player.events.db.entity.OrganizationEntity;

public interface OrganizationsRepository extends JpaRepository<OrganizationEntity, Long>, JpaSpecificationExecutor<OrganizationEntity>{
}
