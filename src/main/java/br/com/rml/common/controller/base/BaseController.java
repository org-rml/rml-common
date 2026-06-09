package br.com.rml.common.controller.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.rml.common.domain.base.BaseEntity;
import br.com.rml.common.dto.base.BaseDTO;
import br.com.rml.common.service.base.BaseServiceMapper;

public abstract class BaseController<
        TEntity extends BaseEntity<ID>,
        TDto extends BaseDTO<ID>,
        TService extends BaseServiceMapper<TEntity, TDto, ID>,
        ID extends Object> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    protected TService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TDto> findById(@PathVariable("id") ID id) {
        TDto dto = service.findDtoById(id);
        return new ResponseEntity<>(dto, dto == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "/list/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TDto>> findByIds(@PathVariable("ids") List<ID> ids) {
        return new ResponseEntity<>(service.findDtoByIds(ids), HttpStatus.OK);
    }
}
