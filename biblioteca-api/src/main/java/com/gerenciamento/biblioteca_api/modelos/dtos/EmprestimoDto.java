package com.gerenciamento.biblioteca_api.modelos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
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


  public EmprestimoDto(Emprestimo emprestimo) {
    this.id = emprestimo.getId();
    this.dataEmprestimo = emprestimo.getDataEmprestimo();
    this.dataDevolucao = emprestimo.getDataDevolucao();
    this.usuarioId = emprestimo.getUsuario().getId();
    this.livroId = emprestimo.getLivros().getId();
  }

}
