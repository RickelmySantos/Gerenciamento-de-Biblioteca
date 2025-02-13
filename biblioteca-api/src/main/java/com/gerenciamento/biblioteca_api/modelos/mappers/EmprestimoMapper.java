package com.gerenciamento.biblioteca_api.modelos.mappers;

import com.gerenciamento.biblioteca_api.core.BaseMapper;
import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LivrosMapper.class, UsuarioMapper.class})
public interface EmprestimoMapper extends BaseMapper<EmprestimoDto, Emprestimo> {

  // EmprestimoMapper INSTANCE = Mappers.getMapper(EmprestimoMapper.class);


  @Override
  @Mapping(source = "usuario", target = "usuarioDto")
  @Mapping(source = "livros", target = "livrosDto")
  EmprestimoDto paraDto(Emprestimo emprestimo);



  @Override
  @Mapping(source = "usuarioDto", target = "usuario")
  @Mapping(source = "livrosDto", target = "livros")
  Emprestimo paraEntidade(EmprestimoDto emprestimoDto);

}
