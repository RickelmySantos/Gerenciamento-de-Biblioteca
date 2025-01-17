package com.gerenciamento.biblioteca_api.core;

import org.mapstruct.MapperConfig;

@MapperConfig
public interface BaseMapper<D, E> {

  E paraEntidade(D dto);

  D paraDto(E entity);
}
