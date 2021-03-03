package ru.moa.player.events.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.moa.player.events.service.OrganizationTypesService;
import ru.moa.player.events.service.OrganizationsService;
import ru.moa.player.events.web.transfer.OrganizationDto;
import ru.moa.player.events.web.transfer.OrganizationTypeDto;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@Transactional
@RequestMapping(value = "/organizations")
public class OrganizationsController {
    private final OrganizationsService organizationsService;
    private final OrganizationTypesService organizationTypesService;

    @GetMapping
    public List<OrganizationDto> getOrganizåtions(){
        return organizationsService.convert(organizationsService.findAll());
    }

    @GetMapping("/{id}")
    public OrganizationDto getOrganizåtions(@PathVariable("id") Long id){
        log.debug("id = {}", id);
        return organizationsService.convert(organizationsService.findById(id));
    }

    @GetMapping("/types")
    public List<OrganizationTypeDto> getOrganizationTypes(){
        return organizationTypesService.convert(organizationTypesService.findAll());
    }

    @PostMapping("/save")
    public OrganizationDto save(
            @RequestBody OrganizationDto organizationDto
    ){
        return organizationsService.convert(organizationsService.save(organizationDto));
    }

    @DeleteMapping("/delete")
    public void delete(
            @RequestBody OrganizationDto organizationDto
    ){
        organizationsService.delete(organizationDto);
    }
}
