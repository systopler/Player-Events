package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "people")
@Setter
public class PeopleEntity extends DeletableEntity<Long> {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;

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
}
