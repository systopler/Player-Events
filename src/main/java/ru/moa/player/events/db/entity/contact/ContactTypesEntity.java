package ru.moa.player.events.db.entity.contact;

import lombok.Setter;
import lombok.ToString;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "contact_types")
@Setter
@ToString(callSuper = true)
public class ContactTypesEntity extends DeletableEntity<Long> {
    private String meaning;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_type_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "meaning")
    public String getMeaning() {
        return meaning;
    }
}
