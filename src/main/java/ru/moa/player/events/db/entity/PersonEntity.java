package ru.moa.player.events.db.entity;

import lombok.Setter;
import org.hibernate.annotations.Where;
import ru.moa.player.events.db.entity.common.DeletableEntity;
import ru.moa.player.events.db.entity.contact.PersonContactEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "people")
@Setter
public class PersonEntity extends DeletableEntity<Long> {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;

    private List<PersonContactEntity> contacts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "last_name")
    public String getLastName(){
        return this.lastName;
    }

    @Column(name = "first_name")
    public String getFirstName(){
        return this.firstName;
    }

    @Column(name = "date_of_birth")
    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }

    @OneToMany(mappedBy = "personEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause="object_type='PERSON'")
    public List<PersonContactEntity> getContacts() {
        return contacts;
    }

    public void addContact(PersonContactEntity contactEntity){
        if (contacts == null){
            contacts = new ArrayList<>();
        }

        contacts.add(contactEntity);
        contactEntity.setPerson(this);
    }

    public void removeContact(PersonContactEntity personContactEntity){
        if (contacts == null){
            for (Iterator<PersonContactEntity> i = contacts.iterator(); i.hasNext();) {
                final PersonContactEntity quesAns = i.next();
                if (quesAns.equals(personContactEntity)) {
                    i.remove();
                    personContactEntity.setPerson(null);
                    return;
                }
            }
        }
    }
}
