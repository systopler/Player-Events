package ru.moa.player.events.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.moa.player.events.service.PeopleService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/people")
public class PeopleController {
    private final PeopleService peopleService;

    @GetMapping("/save")
    @Transactional
    public void save(){
        peopleService.save();
    }
}
