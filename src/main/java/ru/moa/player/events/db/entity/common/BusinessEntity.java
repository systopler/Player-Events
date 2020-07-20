package ru.moa.player.events.db.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessEntity <Id extends Serializable> extends CoreEntity<Id> {
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    @PrePersist
    public void prePersist(){
        this.creationDate = LocalDateTime.now();
        this.lastUpdateDate = this.creationDate;


        if(lastUpdatedBy == null){
            lastUpdatedBy = createdBy;
        }
    }

    @PreUpdate
    public void preUpdate(){
        this.lastUpdateDate = LocalDateTime.now();
    }
}
