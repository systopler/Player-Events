package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "organization_colors")
@Setter
public class OrganizationColorEntity extends DeletableEntity<Long> {
    private OrganizationEntity organization;
    private Long color;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_color_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "color")
    public Long getColor() {
        return color;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    public OrganizationEntity getOrganization() {
        return organization;
    }
}
