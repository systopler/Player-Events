package ru.moa.player.events.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.moa.player.events.web.transfer.SystemDto;
import ru.moa.player.events.service.SystemService;

@AllArgsConstructor
@RestController
@RequestMapping("/system")
public class SystemController {
    private final SystemService systemService;

    @GetMapping()
    public SystemDto system(){
        return systemService.system();
    }
}
