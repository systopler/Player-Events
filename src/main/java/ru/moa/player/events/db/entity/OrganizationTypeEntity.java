package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "organization_types")
@Setter
public class OrganizationTypeEntity extends DeletableEntity<Long> {
    private String meaning;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_type_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "meaning")
    public String getMeaning() {
        return meaning;
    }
}
