package ru.moa.player.events.db.entity.common;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
public class BusinessEntity <Id extends Serializable> extends CoreEntity<Id> {
    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;

    @Column(name = "CREATED_BY")
    public Long getCreatedBy() {
        return createdBy;
    }

    @Column(name = "CREATION_DATE")
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Column(name = "LAST_UPDATED_BY")
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Column(name = "LAST_UPDATE_DATE")
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    @PrePersist
    public void prePersist(){
        this.creationDate = LocalDateTime.now();
        this.lastUpdateDate = this.creationDate;

        this.createdBy = -1L;

        if(lastUpdatedBy == null){
            lastUpdatedBy = createdBy;
        }
    }

    @PreUpdate
    public void preUpdate(){
        this.lastUpdateDate = LocalDateTime.now();
    }
}
