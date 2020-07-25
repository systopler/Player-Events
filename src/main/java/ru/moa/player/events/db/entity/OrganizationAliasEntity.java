package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "organization_aliases")
@Setter
public class OrganizationAliasEntity extends DeletableEntity<Long> {
    private OrganizationEntity organization;
    private String meaning;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_alias_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "meaning")
    public String getMeaning() {
        return meaning;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    public OrganizationEntity getOrganization() {
        return organization;
    }
}
