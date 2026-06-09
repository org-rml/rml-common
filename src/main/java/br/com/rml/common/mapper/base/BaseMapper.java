package br.com.rml.common.mapper.base;

import java.util.List;

import br.com.rml.common.domain.base.BaseEntity;
import br.com.rml.common.dto.base.BaseDTO;

public interface BaseMapper<TEntity extends BaseEntity<?>, TDto extends BaseDTO<?>> {

    TDto toDTO(TEntity entity);

    List<TDto> toDTOs(List<TEntity> entities);

    TEntity toEntity(TDto dto);

    List<TEntity> toEntity(List<TDto> dto);
}
