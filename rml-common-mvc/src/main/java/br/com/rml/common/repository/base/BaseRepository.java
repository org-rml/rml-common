package br.com.rml.common.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.rml.common.domain.base.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<TEntity extends BaseEntity<?>, T extends Object>
        extends JpaRepository<TEntity, T>, JpaSpecificationExecutor<TEntity> {

}
