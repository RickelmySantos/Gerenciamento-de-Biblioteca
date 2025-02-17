package com.gerenciamento.biblioteca_api.modelos.dtos;

import com.gerenciamento.biblioteca_api.modelos.enums.StatusLivro;
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
public class LivroRequestDto {

  private String titulo;
  private String editora;
  private String idioma;
  private String genero;
  private Long autorId;
  private StatusLivro status;

}
