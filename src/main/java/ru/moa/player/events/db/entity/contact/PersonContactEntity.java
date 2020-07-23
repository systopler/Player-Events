package ru.moa.player.events.db.entity.contact;

import lombok.Setter;
import lombok.ToString;
import ru.moa.player.events.db.entity.PersonEntity;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Setter
@ToString(callSuper = true)
public class PersonContactEntity extends DeletableEntity<Long> {
    private PersonEntity person;
    private ContactTypesEntity contactTypesEntity;
    private String objectType;
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_type_id")
    public ContactTypesEntity getContactTypesEntity() {
        return contactTypesEntity;
    }


    @Column(name = "object_type")
    public String getObjectType() {
        return objectType;
    }

    @Column(name = "contact_value")
    public String getValue() {
        return value;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    public PersonEntity getPerson() {
        return person;
    }
}
