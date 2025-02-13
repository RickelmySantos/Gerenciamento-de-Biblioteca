package com.gerenciamento.biblioteca_api.modelos.entidades;

import com.gerenciamento.biblioteca_api.modelos.enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "tb_usuario")
public class Usuario {
  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 255)
  private String id; // Alteado para String devido ao retorno do keycloak ser um UUID

  @NotBlank
  @Size(min = 5, max = 100)
  private String nome;

  private String email;

  @Enumerated(EnumType.STRING)
  private TipoUsuario tipoUsuario;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Emprestimo> emprestimo;
}
