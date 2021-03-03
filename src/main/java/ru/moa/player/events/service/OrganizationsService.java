package ru.moa.player.events.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.moa.player.events.db.entity.OrganizationEntity;
import ru.moa.player.events.db.repository.OrganizationTypeRepository;
import ru.moa.player.events.db.repository.OrganizationsRepository;
import ru.moa.player.events.exception.NotFoundException;
import ru.moa.player.events.web.transfer.OrganizationDto;
import ru.moa.player.events.web.transfer.OrganizationTypeDto;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class OrganizationsService {
    private final MessageService messageService;
    private final OrganizationsRepository organizationsRepository;
    private final OrganizationTypeRepository organizationTypeRepository;
    private final ModelMapper modelMapper;

    public OrganizationEntity convert(OrganizationDto organizationDto){
        if (organizationDto == null){
            return null;
        }
        return modelMapper.map(organizationDto, OrganizationEntity.class);
    }

    public OrganizationDto convert(OrganizationEntity organizationEntity){
        if (organizationEntity == null){
            return null;
        }
        OrganizationDto result = new OrganizationDto();
        result.setFullName(organizationEntity.getFullName());
        result.setPackName(organizationEntity.getPackName());
        result.setCreatedBy(organizationEntity.getCreatedBy());
        result.setObjectVersionNumber(organizationEntity.getObjectVersionNumber());
        result.setCreationDate(organizationEntity.getCreationDate());
        result.setId(organizationEntity.getId());

        if (organizationEntity.getOrganizationType() != null){
            result.setOrganizationType(modelMapper.map(organizationEntity.getOrganizationType(), OrganizationTypeDto.class));
        }


        return result;
    }

    public List<OrganizationDto> convert(List<OrganizationEntity> organizationEntityList){
        return organizationEntityList.stream().map(element -> convert(element)).collect(Collectors.toList());
    }

    private Specification<OrganizationEntity> getSpecification(OrganizationDto organizationDto){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(
                    criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("name_full")), organizationDto.getFullName())
            );
            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            return p.length == 0 ? null : p.length == 1 ? p[0] : criteriaBuilder.and(p);
        };
    }

    public List<OrganizationEntity> findAll(OrganizationDto organizationDto){
        return organizationsRepository.findAll(getSpecification(organizationDto));
    }

    public List<OrganizationEntity> findAll(){
        return organizationsRepository.findAll();
    }

    public OrganizationEntity findById(Long id){
        return organizationsRepository.findById(id).orElseThrow(()-> new NotFoundException(messageService.getMessage("entity.not.found.exception")));
    }

    public OrganizationEntity save(OrganizationDto organizationDto){
        OrganizationEntity organizationEntity;
        if (organizationDto.getId() != null){
            organizationEntity = findById(organizationDto.getId());
            organizationEntity.applyObjectVersionNumber(organizationDto.getObjectVersionNumber());
            organizationEntity.setObjectVersionNumber(organizationEntity.getObjectVersionNumber() + 1);
        } else {
            organizationEntity = new OrganizationEntity();
            organizationEntity.setObjectVersionNumber(1L);
        }

        organizationEntity.setPackName(organizationDto.getPackName());
        organizationEntity.setFullName(organizationDto.getFullName());


        if (organizationDto.getOrganizationType() != null){
            organizationEntity.setOrganizationType(organizationTypeRepository.findById(organizationDto.getOrganizationType().getId()).orElse(null));
        }



        //return organizationEntity;
        return organizationsRepository.saveAndFlush(organizationEntity);
    }

    public void delete(OrganizationDto organizationDto){
        OrganizationEntity organizationEntity = findById(organizationDto.getId());
        organizationEntity.applyObjectVersionNumber(organizationDto.getObjectVersionNumber());
        organizationsRepository.delete(organizationEntity);
    }
}
