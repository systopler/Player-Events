package ru.moa.player.events.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.moa.player.events.db.repository.FileRepository;

import javax.servlet.ServletContext;

@AllArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    private final ServletContext context;
}
