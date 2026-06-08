package com.rml.common.dto.base;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public abstract class BaseDTO<ID extends Object> {

    protected ID id;

    protected Calendar dataCriacao;

    protected Calendar dataAlteracao;

    public String toJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    @JsonIgnore
    public boolean isIdNull() {
        return id == null;
    }

    @JsonIgnore
    public boolean isIdNotNull() {
        return id != null;
    }
}
