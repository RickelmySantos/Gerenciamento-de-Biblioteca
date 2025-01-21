package com.gerenciamento.biblioteca_api.modelos.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EmprestimoDto {

  private Long id;
  private LocalDate dataEmprestimo;
  private LocalDate dataDevolucao;
  private Long usuarioId;
  private Long livroId;
}
