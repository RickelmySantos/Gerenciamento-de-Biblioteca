package com.gerenciamento.biblioteca_api.modelos.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Table(name = "tb_autores")
public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  @Size(min = 5, max = 100)
  private String nome;
  @NotBlank
  @Size(min = 5, max = 100)
  private String sobrenome;
  @NotBlank
  private String nacionalidade;

  private LocalDate dataNascimento;

  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Livros> livros;
}
