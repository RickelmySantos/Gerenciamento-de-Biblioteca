package com.gerenciamento.biblioteca_api.modelos.dtos;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AutorDto {

  private Long id;
  private String nome;
  private String sobrenome;
  private String nacionalidade;
  private LocalDate dataNascimento;
  private List<LivrosDto> livros;
}
