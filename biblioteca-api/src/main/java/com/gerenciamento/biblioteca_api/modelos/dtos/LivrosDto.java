package com.gerenciamento.biblioteca_api.modelos.dtos;

import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;
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
public class LivrosDto {
  private Long id;
  private String titulo;
  private String editora;
  private String idioma;
  private String genero;

  private Autor autor;
  private List<EmprestimoDto> emprestimo;

}
