package com.gerenciamento.biblioteca_api.modelos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
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
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataNascimento;
}
