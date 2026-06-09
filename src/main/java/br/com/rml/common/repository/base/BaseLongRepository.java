package br.com.rml.common.repository.base;

import org.springframework.data.repository.NoRepositoryBean;

import br.com.rml.common.domain.base.BaseLongEntity;

@NoRepositoryBean
public interface BaseLongRepository<TEntity extends BaseLongEntity> extends BaseRepository<TEntity, Long> {

}
