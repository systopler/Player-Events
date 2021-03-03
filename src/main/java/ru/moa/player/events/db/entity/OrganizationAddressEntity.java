package ru.moa.player.events.db.entity;

import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Setter
@ToString(callSuper = true)
@Where(clause = "object_type = 'ORGANIZATION'")
public class OrganizationAddressEntity extends DeletableEntity<Long> {
    private OrganizationEntity organization;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    public OrganizationEntity getOrganization() {
        return organization;
    }
}
