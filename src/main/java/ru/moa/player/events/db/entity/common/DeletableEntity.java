package ru.moa.player.events.db.entity.common;

import javax.persistence.Column;
import javax.persistence.PreRemove;
import java.io.Serializable;
import java.time.LocalDateTime;

public class DeletableEntity <Id extends Serializable> extends VersionedEntity<Id>{
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @PreRemove
    public void remove(){
        this.deleteDate = LocalDateTime.now();
    }
}
