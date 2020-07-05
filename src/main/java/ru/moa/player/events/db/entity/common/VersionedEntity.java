package ru.moa.player.events.db.entity.common;

import javax.persistence.Column;
import javax.persistence.Version;
import java.io.Serializable;

public class VersionedEntity <Id extends Serializable> extends BusinessEntity<Id>{
    @Version
    @Column(name = "OBJECT_VERSION_NUMBER", nullable = false)
    private Long version;
}
