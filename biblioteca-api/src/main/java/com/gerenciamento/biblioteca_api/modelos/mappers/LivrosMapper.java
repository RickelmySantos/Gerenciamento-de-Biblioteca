package com.gerenciamento.biblioteca_api.modelos.mappers;

import com.gerenciamento.biblioteca_api.core.BaseMapper;
import com.gerenciamento.biblioteca_api.modelos.dtos.LivrosDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import org.mapstruct.Mapper;

@Mapper
public interface LivrosMapper extends BaseMapper<LivrosDto, Livros> {



}
