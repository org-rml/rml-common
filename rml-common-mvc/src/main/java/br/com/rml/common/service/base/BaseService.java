package br.com.rml.common.service.base;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rml.common.domain.base.BaseEntity;

public interface BaseService<TEntity extends BaseEntity<ID>, ID extends Object> {

    TEntity findById(ID id);

    List<TEntity> findByIds(final Collection<ID> ids);

    TEntity save(TEntity entity) throws Exception;

    List<TEntity> save(List<TEntity> entities) throws Exception;

    List<TEntity> findAll();

    Page<TEntity> findAll(Pageable pageable);

    Page<TEntity> findAll(Pageable pageable, String termo);

    void remove(TEntity entity) throws Exception;

    long countAll();

    Optional<TEntity> findOptionalById(ID id);
}
