package com.gerenciamento.biblioteca_api.repositorios;

import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

  boolean existsByLivrosAndDataDevolucaoAfter(Livros livro, LocalDate dataDevolucao);

  @Query("SELECT e FROM Emprestimo e JOIN FETCH e.usuario JOIN FETCH e.livros WHERE e.livros.id = :livroId")
  List<Emprestimo> findByLivroId(@Param("livroId") Long livroId);

}
