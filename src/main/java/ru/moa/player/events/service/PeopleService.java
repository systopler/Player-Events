package ru.moa.player.events.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.moa.player.events.db.entity.PersonEntity;
import ru.moa.player.events.db.repository.PeopleRepository;

@Slf4j
@AllArgsConstructor
@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public void save(){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setLastName("Митюшин");
        personEntity.setFirstName("Олег");
        personEntity.setVersion(1L);
        peopleRepository.save(personEntity);
    }
}
