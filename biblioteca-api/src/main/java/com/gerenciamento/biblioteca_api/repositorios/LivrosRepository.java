package com.gerenciamento.biblioteca_api.repositorios;

import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivrosRepository extends JpaRepository<Livros, Long> {

  @Query("SELECT l FROM Livros l JOIN FETCH l.autor WHERE l.id = :id")
  Optional<Livros> findByWithAutor(@Param("id") Long id);

}
