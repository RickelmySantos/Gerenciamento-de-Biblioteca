package com.gerenciamento.biblioteca_api.modelos.mappers;

import com.gerenciamento.biblioteca_api.core.BaseMapper;
import com.gerenciamento.biblioteca_api.modelos.dtos.LivrosDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivrosMapper extends BaseMapper<LivrosDto, Livros> {

  @Override
  @Mapping(source = "autor.id", target = "autor.id")
  Livros paraEntidade(LivrosDto dto);


  @Override
  @Mapping(source = "autor.id", target = "autor.id")
  @Mapping(source = "autor.nome", target = "autor.nome")
  @Mapping(source = "autor.sobrenome", target = "autor.sobrenome")
  LivrosDto paraDto(Livros entity);

}
