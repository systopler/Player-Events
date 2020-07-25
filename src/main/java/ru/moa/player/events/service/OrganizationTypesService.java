package ru.moa.player.events.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.moa.player.events.db.entity.OrganizationTypeEntity;
import ru.moa.player.events.db.repository.OrganizationTypeRepository;
import ru.moa.player.events.web.transfer.OrganizationTypeDto;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class OrganizationTypesService {
    private final OrganizationTypeRepository organizationTypeRepository;
    private final ModelMapper modelMapper;

    public OrganizationTypeDto convert(OrganizationTypeEntity organizationTypeEntity){
        return modelMapper.map(organizationTypeEntity, OrganizationTypeDto.class);
    }

    public List<OrganizationTypeDto> convert(List<OrganizationTypeEntity> organizationTypeEntityList){
        return organizationTypeEntityList.stream().map(element -> convert(element)).collect(Collectors.toList());
    }

    public OrganizationTypeEntity findById(Long id){
        return organizationTypeRepository.getOne(id);
    }

    public List<OrganizationTypeEntity> findAll(){
        return organizationTypeRepository.findAll();
    }
}
