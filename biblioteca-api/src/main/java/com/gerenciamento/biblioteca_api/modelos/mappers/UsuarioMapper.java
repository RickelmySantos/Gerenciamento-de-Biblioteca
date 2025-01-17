package com.gerenciamento.biblioteca_api.modelos.mappers;

import com.gerenciamento.biblioteca_api.core.BaseMapper;
import com.gerenciamento.biblioteca_api.modelos.dtos.UsuarioDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper extends BaseMapper<UsuarioDto, Usuario> {

}
