package br.com.rml.common.service.base;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rml.common.domain.base.BaseEntity;
import br.com.rml.common.dto.base.BaseDTO;

public interface BaseServiceMapper<TEntity extends BaseEntity<ID>, TDto extends BaseDTO<ID>, ID extends Object>
        extends BaseService<TEntity, ID> {

    TDto toDTO(TEntity entity);

    List<TDto> toDTOs(List<TEntity> entities);

    TDto findDtoById(ID id);

    List<TDto> findDtoByIds(final Collection<ID> ids);

    List<TDto> findAllDto();

    Page<TDto> findAllDto(Pageable pageable);

    Page<TDto> findAllDto(Pageable pageable, String termo);

    TEntity save(TDto dto) throws Exception;

    List<TEntity> save(List<TEntity> entities) throws Exception;

    Page<TDto> toPageDto(Page<TEntity> page);
}
