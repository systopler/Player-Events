package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;
import ru.moa.player.events.db.entity.contact.PersonContactEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "organizations")
@Setter
public class OrganizationEntity extends DeletableEntity<Long> {
    private String packName;
    private String fullName;
    private OrganizationTypeEntity organizationTypeEntity;
    private List<OrganizationAliasEntity> organizationAliasEntityList;

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


    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrganizationAliasEntity> getOrganizationAliasEntityList() {
        return organizationAliasEntityList;
    }

    public void addAlias(OrganizationAliasEntity alias){
        if (organizationAliasEntityList == null){
            organizationAliasEntityList = new ArrayList<>();
        }

        organizationAliasEntityList.add(alias);
        alias.setOrganization(this);
    }

    public void removeAlias(OrganizationAliasEntity alias){
        if (organizationAliasEntityList == null){
            for (Iterator<OrganizationAliasEntity> i = organizationAliasEntityList.iterator(); i.hasNext();) {
                final OrganizationAliasEntity quesAns = i.next();
                if (quesAns.equals(alias)) {
                    i.remove();
                    alias.setOrganization(null);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Organization doesn't have associated alias");
    }

}
