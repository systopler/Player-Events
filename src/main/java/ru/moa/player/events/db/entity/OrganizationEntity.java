package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "organizations")
@Setter
public class OrganizationEntity extends DeletableEntity<Long> {
    private String packName;
    private String fullName;
    private OrganizationTypeEntity organizationTypeEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "name_pack")
    public String getPackName() {
        return packName;
    }

    @Column(name = "name_full")
    public String getFullName() {
        return fullName;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_type_id")
    public OrganizationTypeEntity getOrganizationTypeEntity() {
        return organizationTypeEntity;
    }
}
