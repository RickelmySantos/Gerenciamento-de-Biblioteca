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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EmprestimoDto {

  private Long id;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataEmprestimo;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataDevolucao;
  private Long usuarioId;
  private Long livroId;
}
