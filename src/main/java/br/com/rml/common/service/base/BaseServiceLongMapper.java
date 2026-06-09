package br.com.rml.common.service.base;

import br.com.rml.common.domain.base.BaseLongEntity;
import br.com.rml.common.dto.base.BaseLongDTO;

public interface BaseServiceLongMapper<TEntity extends BaseLongEntity, TDto extends BaseLongDTO>
        extends BaseServiceMapper<TEntity, TDto, Long> {

}
