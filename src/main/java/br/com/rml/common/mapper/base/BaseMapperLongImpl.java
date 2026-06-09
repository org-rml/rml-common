package br.com.rml.common.mapper.base;

import br.com.rml.common.domain.base.BaseLongEntity;

public abstract class BaseMapperLongImpl<TEntity extends BaseLongEntity> extends BaseMapperImpl<TEntity, Long> {

    public BaseMapperLongImpl(Class<TEntity> clazz) {
        super(clazz);
    }
}
