package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Setter
public class ContactEntity extends DeletableEntity<Long> {
    private ContactTypesEntity contactTypesEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_type_id", referencedColumnName = "id")
    public ContactTypesEntity getContactTypesEntity() {
        return contactTypesEntity;
    }
}
