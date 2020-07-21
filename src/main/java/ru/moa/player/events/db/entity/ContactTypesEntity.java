package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "contact_types")
@Setter
public class ContactTypesEntity extends DeletableEntity<Long> {
    @OneToOne(mappedBy = "contactTypesEntity")
    private ContactEntity contactEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_type_id")
    @Override
    public Long getId() {
        return super.getId();
    }
}
