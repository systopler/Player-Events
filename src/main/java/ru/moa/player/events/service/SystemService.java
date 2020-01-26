package ru.moa.player.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.moa.player.events.dto.SystemDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class SystemService {
    private final MessageService messageService;

    public SystemDto system(){
        return SystemDto.builder().email(messageService.getMessage("system.email")).build();
    }

}
