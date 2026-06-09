package br.com.rml.common.domain.base;

import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class BaseLongEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 8243944500509434189L;
}
