package ru.moa.player.events.db.entity;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "organization_types")
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
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
