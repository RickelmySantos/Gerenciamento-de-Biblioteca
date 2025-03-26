package com.gerenciamento.biblioteca_api.modelos.mappers;

import com.gerenciamento.biblioteca_api.core.BaseMapper;
import com.gerenciamento.biblioteca_api.modelos.dtos.CategoriaDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<CategoriaDto, Categoria> {

}
