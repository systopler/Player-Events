package ru.moa.player.events.db.entity.common;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
public class VersionedEntity <Id extends Serializable> extends BusinessEntity<Id>{
    @Version
    private Long version;

    @Column(name = "OBJECT_VERSION_NUMBER", nullable = false)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
