package com.rml.common.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rml.common.domain.base.BaseEntity;

public interface BuscavelPeloNomeRepository<TEntity extends BaseEntity<?>> {

    Page<TEntity> findAllByNomeIgnoreCaseContaining(Pageable pageable, String nome);
}
