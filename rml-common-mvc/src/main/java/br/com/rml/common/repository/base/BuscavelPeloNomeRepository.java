package br.com.rml.common.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rml.common.domain.base.BaseEntity;

public interface BuscavelPeloNomeRepository<TEntity extends BaseEntity<?>> {

    Page<TEntity> findAllByNomeIgnoreCaseContaining(Pageable pageable, String nome);
}
