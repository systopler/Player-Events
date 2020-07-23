package ru.moa.player.events.db.entity.common;

import lombok.ToString;

import java.io.Serializable;

@ToString(of = {"id"})
public class CoreEntity<Id extends Serializable> implements Identifiable<Id> {

    protected Id id;
    private Integer hashCode = null;

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public synchronized int hashCode() {
        if (hashCode == null && id != null){
            hashCode = 31 + id.hashCode();
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        CoreEntity<?> other = (CoreEntity<?>)obj;

        if (id == null){
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;

        return true;
    }
}
