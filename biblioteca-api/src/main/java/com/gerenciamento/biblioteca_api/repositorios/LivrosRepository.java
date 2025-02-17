package com.gerenciamento.biblioteca_api.repositorios;

import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivrosRepository extends JpaRepository<Livros, Long> {

  // @Query("SELECT l FROM Livros l JOIN FETCH l.autor WHERE l.id = :id")
  @Query("SELECT l FROM Livros l JOIN FETCH l.autor LEFT JOIN FETCH l.emprestimo WHERE l.id = :id")
  Optional<Livros> findByIdWithAutor(@Param("id") Long id);

  @Query("SELECT COUNT(e) > 0 FROM Emprestimo e WHERE e.livros.id = :livroId AND e.dataDevolucao IS NULL")
  boolean existsEmprestimo(@Param("livroId") long livroId);


  @Override
  @EntityGraph(attributePaths = {"autor", "emprestimo"})
  List<Livros> findAll();
}
