package com.gerenciamento.biblioteca_api.modelos.dtos;

import com.gerenciamento.biblioteca_api.modelos.enums.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
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
public class UsuarioDto {

  private Long id;
  @NotBlank
  @Size(min = 5, max = 100)
  private String nome;
  private String email;
  private String senha;
  private TipoUsuario tipoUsuario;

  private List<EmprestimoDto> emprestimoDto;
}
