package br.com.rml.common.service.base;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.rml.common.domain.base.BaseEntity;
import br.com.rml.common.dto.base.BaseDTO;
import br.com.rml.common.mapper.base.BaseMapper;

@Transactional
public abstract class BaseServiceMapperImpl<TEntity extends BaseEntity<ID>, TDto extends BaseDTO<ID>, ID extends Object>
        extends BaseServiceImpl<TEntity, ID> implements BaseServiceMapper<TEntity, TDto, ID> {

    @Autowired(required = false)
    protected BaseMapper<TEntity, TDto> mapper;

    public BaseServiceMapperImpl(Class<TEntity> typeBase) {
        super(typeBase);
    }

    @Override
    public TDto toDTO(TEntity entity) {
        return mapper.toDTO(entity);
    }

    @Override
    public List<TDto> toDTOs(List<TEntity> entities) {
        return mapper.toDTOs(entities);
    }

    @Override
    public TEntity save(TDto dto) throws Exception {
        TEntity entity = this.mapper.toEntity(dto);
        return super.save(entity);
    }

    @Override
    public TDto findDtoById(ID id) {
        return toDTO(findById(id));
    }

    @Override
    public List<TDto> findDtoByIds(Collection<ID> ids) {
        return toDTOs(findByIds(ids));
    }

    @Override
    public List<TDto> findAllDto() {
        return toDTOs(findAll());
    }

    @Override
    public Page<TDto> findAllDto(Pageable pageable) {
        return findAllDto(pageable, null);
    }

    @Override
    public Page<TDto> findAllDto(Pageable pageable, String termo) {
        Page<TEntity> page = findAll(pageable, termo);
        return toPageDto(page);
    }

    @Override
    public Page<TDto> toPageDto(Page<TEntity> page) {
        List<TDto> dtos = toDTOs(page.getContent());
        return new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
    }
}
