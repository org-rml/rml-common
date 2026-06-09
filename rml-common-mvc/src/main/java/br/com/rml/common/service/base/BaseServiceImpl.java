package br.com.rml.common.service.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.rml.common.domain.base.BaseEntity;
import br.com.rml.common.repository.base.BaseRepository;
import br.com.rml.common.repository.base.BuscavelPeloNomeRepository;

@Transactional
public abstract class BaseServiceImpl<TEntity extends BaseEntity<ID>, ID extends Object>
        implements BaseService<TEntity, ID> {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected Class<TEntity> typeBase;

    @Autowired(required = false)
    protected BaseRepository<TEntity, ID> repository;

    @Autowired(required = false)
    protected BuscavelPeloNomeRepository<TEntity> buscavelPeloNomeRepository;

    public BaseServiceImpl(Class<TEntity> typeBase) {
        this.typeBase = typeBase;
    }

    @Override
    public TEntity findById(ID id) {
        Assert.notNull(id, "ID não pode ser nulo. Entity: " + typeBase.getName());
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<TEntity> findByIds(final Collection<ID> ids) {
        Assert.notEmpty(ids, "ids");
        return repository.findAllById(ids);
    }

    @Override
    public void remove(TEntity entity) throws Exception {
        validateBeforeRemove(entity);
        repository.delete(entity);
    }

    @Override
    public TEntity save(TEntity entity) throws Exception {
        beforePersist(entity);
        TEntity savedEntity = repository.save(entity);
        afterPersist(entity);
        return savedEntity;
    }

    @Override
    public List<TEntity> save(List<TEntity> entities) throws Exception {
        List<TEntity> result = new ArrayList<>();
        if (entities == null) return result;
        for (TEntity entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    @Override
    public List<TEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<TEntity> findAll(Pageable pageable) {
        return findAll(pageable, null);
    }

    @Override
    public Page<TEntity> findAll(Pageable pageable, String termo) {
        if (pageable == null) pageable = PageRequest.of(0, 10);
        if (StringUtils.isNotBlank(termo) && buscavelPeloNomeRepository != null) {
            return buscavelPeloNomeRepository.findAllByNomeIgnoreCaseContaining(pageable, termo);
        }
        return repository.findAll(pageable);
    }

    @Override
    public long countAll() {
        return repository.count();
    }

    @Override
    public Optional<TEntity> findOptionalById(ID id) {
        Assert.notNull(id, "ID não pode ser nulo. Entity: " + typeBase.getName());
        return repository.findById(id);
    }

    private void beforePersist(TEntity entity) throws Exception {
        if (entity.getId() != null) {
            validateBeforeUpdate(entity);
        } else {
            validateBeforeSave(entity);
        }
    }

    protected void validateBeforeSave(TEntity entity) throws Exception { }

    protected void validateBeforeUpdate(TEntity entity) throws Exception { }

    protected void validateBeforeRemove(TEntity entity) throws Exception { }

    protected void afterPersist(TEntity entity) throws Exception { }
}
