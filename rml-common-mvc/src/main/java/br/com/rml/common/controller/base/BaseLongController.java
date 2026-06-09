package br.com.rml.common.controller.base;

import br.com.rml.common.domain.base.BaseLongEntity;
import br.com.rml.common.dto.base.BaseLongDTO;
import br.com.rml.common.service.base.BaseServiceLongMapper;

public abstract class BaseLongController<
        TEntity extends BaseLongEntity,
        TDto extends BaseLongDTO,
        TServiceMapper extends BaseServiceLongMapper<TEntity, TDto>>
        extends BaseController<TEntity, TDto, TServiceMapper, Long> {

}
