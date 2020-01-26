package ru.moa.player.events.db.entity.common;

import java.io.Serializable;

public interface Identifiable<Id extends Serializable> extends Serializable {
    public Id getId();
    public void setId(Id id);
}
