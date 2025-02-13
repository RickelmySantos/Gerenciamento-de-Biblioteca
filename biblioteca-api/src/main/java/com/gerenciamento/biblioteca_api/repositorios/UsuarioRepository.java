package com.gerenciamento.biblioteca_api.repositorios;

import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

  Optional<Usuario> findByEmail(String email);
}
