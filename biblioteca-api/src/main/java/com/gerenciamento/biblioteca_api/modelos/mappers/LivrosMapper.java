package com.gerenciamento.biblioteca_api.modelos.mappers;

import com.gerenciamento.biblioteca_api.core.BaseMapper;
import com.gerenciamento.biblioteca_api.modelos.dtos.LivroRequestDto;
import com.gerenciamento.biblioteca_api.modelos.dtos.LivrosDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.enums.StatusLivro;
import java.time.LocalDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivrosMapper extends BaseMapper<LivrosDto, Livros> {


  @Mapping(source = "autorId", target = "autor.id")
  Livros paraEntidade(LivroRequestDto dto);

  @Override
  @Mapping(source = "autor.id", target = "autor.id")
  @Mapping(source = "autor.nome", target = "autor.nome")
  @Mapping(source = "autor.sobrenome", target = "autor.sobrenome")
  @Mapping(source = "autor.nacionalidade", target = "autor.nacionalidade")
  @Mapping(source = "autor.dataNascimento", target = "autor.dataNascimento")
  @Mapping(target = "status", expression = "java(definirStatus(entity))")
  LivrosDto paraDto(Livros entity);

  default StatusLivro definirStatus(Livros livro) {
    if (livro.getEmprestimo() != null && !livro.getEmprestimo().isEmpty()) {
      boolean emprestimoAtivo = livro.getEmprestimo().stream()
          .anyMatch(e -> e.getDataDevolucao().isAfter(LocalDate.now()));
      return emprestimoAtivo ? StatusLivro.EMPRESTADO : StatusLivro.DISPONIVEL;
    }
    return StatusLivro.DISPONIVEL;
  }
}
