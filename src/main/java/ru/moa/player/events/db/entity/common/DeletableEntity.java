package ru.moa.player.events.db.entity.common;

import javax.persistence.Column;
import javax.persistence.PreRemove;
import java.io.Serializable;
import java.time.LocalDateTime;

public class DeletableEntity <Id extends Serializable> extends VersionedEntity<Id>{
    private LocalDateTime deleteDate;

    @PreRemove
    public void remove(){
        this.deleteDate = LocalDateTime.now();
    }

    @Column(name = "delete_date")
    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }
}
