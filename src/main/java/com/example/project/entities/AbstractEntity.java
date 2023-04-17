package com.example.project.entities;

import com.example.project.models.AbstractModel;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Abstract base class for entities.
 *
 * @param <T> the type of the entity's primary key
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AbstractEntity<T> extends AbstractModel implements Serializable {

  private T id;
}
