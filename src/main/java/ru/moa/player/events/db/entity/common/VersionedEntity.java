package ru.moa.player.events.db.entity.common;

import javax.persistence.*;
import java.io.Serializable;
import java.text.MessageFormat;

@MappedSuperclass
public class VersionedEntity <Id extends Serializable> extends BusinessEntity<Id>{
    @Version
    private Long objectVersionNumber;

    @Column(name = "OBJECT_VERSION_NUMBER", nullable = false)
    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long version) {
        this.objectVersionNumber = version;
    }

    public void applyObjectVersionNumber(Long objectVersionNumber) {
        if (!this.objectVersionNumber.equals(objectVersionNumber)) {
            throw new OptimisticLockException(MessageFormat.format("Stale entity {0}: applied version {1}, but current version is {2}", this.getClass().getSimpleName(), objectVersionNumber, this.objectVersionNumber));
        }
    }
}
