package br.com.rml.common.mapper.base;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.rml.common.domain.base.BaseEntity;
import br.com.rml.common.domain.base.BaseLongEntity;
import br.com.rml.common.domain.base.BaseStringEntity;
import br.com.rml.common.dto.base.BaseDTO;
import br.com.rml.common.repository.base.BaseRepository;

public abstract class BaseMapperImpl<TEntity extends BaseEntity<ID>, ID extends Object> {

    protected final static Logger LOGGER = LoggerFactory.getLogger(BaseMapperImpl.class);

    private Class<TEntity> typeBase;

    @Autowired(required = false)
    protected BaseRepository<TEntity, ID> repository;

    public BaseMapperImpl(Class<TEntity> clazz) {
        this.typeBase = clazz;
    }

    @ObjectFactory
    public TEntity resolve(BaseDTO<ID> dto) {
        return dto != null ? resolve(dto.getId()) : null;
    }

    public TEntity map(ID id) {
        if (id == null) return null;
        TEntity entity = resolve(id);
        entity.setId(id);
        return entity;
    }

    public Collection<TEntity> mapCollection(Collection<ID> values) {
        Collection<TEntity> entities = new ArrayList<>();
        for (ID value : values) {
            entities.add(this.map(value));
        }
        return entities;
    }

    public Long mapToLong(TEntity entity) {
        if (entity == null) return null;
        return ((BaseLongEntity) entity).getId();
    }

    public String mapToString(TEntity entity) {
        if (entity == null) return null;
        return ((BaseStringEntity) entity).getId();
    }

    public Collection<Long> mapToLongList(List<TEntity> entities) {
        List<Long> ids = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) return ids;
        for (TEntity entity : entities) {
            ids.add(this.mapToLong(entity));
        }
        return ids;
    }

    private TEntity resolve(ID id) {
        TEntity entity = null;
        if (id != null) {
            entity = this.repository.findById(id).orElse(null);
            if (entity == null) {
                LOGGER.warn("EntityNotFound {} para o ID {}", typeBase.getSimpleName(), id);
            }
        }
        try {
            return entity != null ? entity : typeBase.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
