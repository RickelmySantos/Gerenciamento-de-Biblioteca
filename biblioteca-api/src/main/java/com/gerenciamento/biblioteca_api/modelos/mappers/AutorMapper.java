package com.gerenciamento.biblioteca_api.modelos.mappers;

import com.gerenciamento.biblioteca_api.core.BaseMapper;
import com.gerenciamento.biblioteca_api.modelos.dtos.AutorDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper extends BaseMapper<AutorDto, Autor> {

}
