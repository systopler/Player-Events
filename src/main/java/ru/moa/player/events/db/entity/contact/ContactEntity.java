package ru.moa.player.events.db.entity.contact;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "object_type")
@Table(name = "contacts")
@Setter
@ToString(callSuper = true)
public class ContactEntity extends DeletableEntity<Long> {
    private ContactTypesEntity contactTypesEntity;
    private String objectType;
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_type_id")
    public ContactTypesEntity getContactTypesEntity() {
        return contactTypesEntity;
    }


    @Column(name = "object_type", insertable = false, updatable = false)
    public String getObjectType() {
        return objectType;
    }

    @Column(name = "contact_value")
    public String getValue() {
        return value;
    }
}
