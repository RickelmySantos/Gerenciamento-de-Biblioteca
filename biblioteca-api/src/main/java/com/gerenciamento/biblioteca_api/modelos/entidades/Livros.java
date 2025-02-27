package com.gerenciamento.biblioteca_api.modelos.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
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
@EqualsAndHashCode
@Table(name = "tb_livros")
public class Livros {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  private String editora;
  private String idioma;
  private String genero;
  @ManyToOne
  @JoinColumn(name = "autor_id", nullable = false)
  private Autor autor;
  @OneToMany(mappedBy = "livros", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Emprestimo> emprestimo;
}
