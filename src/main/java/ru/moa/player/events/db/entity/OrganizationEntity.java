package ru.moa.player.events.db.entity;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "organizations")
@EqualsAndHashCode(callSuper=true)
@Setter
public class OrganizationEntity extends DeletableEntity<Long> {
    private String packName;
    private String fullName;
    private OrganizationTypeEntity organizationType;

    private List<OrganizationAliasEntity> aliases;
    private List<OrganizationAddressEntity> addresses;
    private List<OrganizationColorEntity> colors;



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


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_type_id")
    public OrganizationTypeEntity getOrganizationType() {
        return organizationType;
    }

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrganizationAliasEntity> getAliases() {
        return aliases;
    }

    public void addAlias(OrganizationAliasEntity alias){
        if (aliases == null){
            aliases = new ArrayList<>();
        }

        aliases.add(alias);
        alias.setOrganization(this);
    }

    public void removeAlias(OrganizationAliasEntity alias){
        if (aliases == null){
            for (Iterator<OrganizationAliasEntity> i = aliases.iterator(); i.hasNext();) {
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

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrganizationAddressEntity> getAddresses() {
        return addresses;
    }

    public void addAddress(OrganizationAddressEntity organizationAddressEntity){
        if (addresses == null){
            addresses = new ArrayList<>();
        }

        addresses.add(organizationAddressEntity);
        organizationAddressEntity.setOrganization(this);

    }

    public void removeAddress(OrganizationAddressEntity organizationAddressEntity){
        if (addresses == null){
            for (Iterator<OrganizationAddressEntity> i = addresses.iterator(); i.hasNext();) {
                final OrganizationAddressEntity quesAns = i.next();
                if (quesAns.equals(organizationAddressEntity)) {
                    i.remove();
                    organizationAddressEntity.setOrganization(null);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Organization doesn't have associated address");
    }

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrganizationColorEntity> getColors() {
        return colors;
    }

    public void addColor(OrganizationColorEntity organizationColorEntity){
        if (colors == null){
            colors = new ArrayList<>();
        }

        colors.add(organizationColorEntity);
        organizationColorEntity.setOrganization(this);

    }

    public void removeColor(OrganizationColorEntity organizationColorEntity){
        if (colors == null){
            for (Iterator<OrganizationColorEntity> i = colors.iterator(); i.hasNext();) {
                final OrganizationColorEntity quesAns = i.next();
                if (quesAns.equals(organizationColorEntity)) {
                    i.remove();
                    organizationColorEntity.setOrganization(null);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Organization doesn't have associated color");
    }


}
