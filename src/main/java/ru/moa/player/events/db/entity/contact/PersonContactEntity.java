package ru.moa.player.events.db.entity.contact;

import lombok.Setter;
import ru.moa.player.events.db.entity.PersonEntity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PERSON")
@Setter
public class PersonContactEntity extends ContactEntity{
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    public PersonEntity getPerson() {
        return person;
    }
}
