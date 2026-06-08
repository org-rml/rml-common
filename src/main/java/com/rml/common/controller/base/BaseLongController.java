package com.rml.common.controller.base;

import com.rml.common.domain.base.BaseLongEntity;
import com.rml.common.dto.base.BaseLongDTO;
import com.rml.common.service.base.BaseServiceLongMapper;

public abstract class BaseLongController<
        TEntity extends BaseLongEntity,
        TDto extends BaseLongDTO,
        TServiceMapper extends BaseServiceLongMapper<TEntity, TDto>>
        extends BaseController<TEntity, TDto, TServiceMapper, Long> {

}
