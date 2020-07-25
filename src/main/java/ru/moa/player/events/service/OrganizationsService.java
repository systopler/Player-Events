package ru.moa.player.events.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.moa.player.events.db.entity.OrganizationEntity;
import ru.moa.player.events.db.repository.OrganizationsRepository;
import ru.moa.player.events.exception.NotFoundException;
import ru.moa.player.events.exception.OptimisticLockException;
import ru.moa.player.events.web.transfer.OrganizationDto;

import javax.persistence.criteria.Predicate;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class OrganizationsService {
    private final MessageService messageService;
    private final OrganizationsRepository organizationsRepository;
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
        return modelMapper.map(organizationEntity, OrganizationDto.class);
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
        if (organizationDto.getId() != null){
            OrganizationEntity organizationEntityOld = organizationsRepository.getOne(organizationDto.getId());
            if (organizationEntityOld == null){
                throw new NotFoundException(messageService.getMessage("entity.not.found.exception"));
            } else {
                if (organizationEntityOld.getVersion().longValue() != organizationDto.getVersion().longValue()){
                    throw new OptimisticLockException(MessageFormat.format("Stale entity {0}: applied version {1}, but current version is {2}", this.getClass().getSimpleName(), organizationDto.getVersion(), organizationEntityOld.getVersion()));
                }
            }
        }
        return organizationsRepository.save(modelMapper.map(organizationDto, OrganizationEntity.class));
    }
}
