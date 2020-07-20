package ru.moa.player.events.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.moa.player.events.db.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
