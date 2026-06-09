package br.com.rml.common.service.base;

import org.springframework.transaction.annotation.Transactional;

import br.com.rml.common.domain.base.BaseLongEntity;
import br.com.rml.common.dto.base.BaseLongDTO;

@Transactional
public abstract class BaseServiceLongMapperImpl<TEntity extends BaseLongEntity, TDto extends BaseLongDTO>
        extends BaseServiceMapperImpl<TEntity, TDto, Long> {

    public BaseServiceLongMapperImpl(Class<TEntity> typeBase) {
        super(typeBase);
    }
}
