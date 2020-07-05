package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "people")
@Setter
public class PeopleEntity extends DeletableEntity<Long> {
}
