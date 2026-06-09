package br.com.rml.common.domain.base;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseStringEntity extends BaseEntity<String> {

    private static final long serialVersionUID = 8243944500509434189L;
}
