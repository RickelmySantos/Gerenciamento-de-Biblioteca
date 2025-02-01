package com.gerenciamento.biblioteca_api.core.modelo;


import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public abstract class EntidadeBaseDTO<E extends EntidadeBase>
    implements Comparable<EntidadeBaseDTO<E>> {

  private static final long serialVersionUID = 1L;

  @Positive
  @ToString.Include
  @EqualsAndHashCode.Include
  protected Long id;

  protected String usuarioCadastro;

  protected LocalDateTime dataHoraCadastro;

  protected String usuarioUltimaAtualizacao;

  protected LocalDateTime dataHoraUltimaAtualizacao;

  protected String usuarioExclusao;

  protected LocalDateTime dataHoraExclusao;

  @Override
  public int compareTo(EntidadeBaseDTO<E> dto) {
    if (this.id == dto.id) {
      return 0;
    }

    if (this.id == null && dto.id != null) {
      return -1;
    }

    if (this.id != null && dto.id == null) {
      return 1;
    }

    return this.id.compareTo(dto.id);
  }

}
