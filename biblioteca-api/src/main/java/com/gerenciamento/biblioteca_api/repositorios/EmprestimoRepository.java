package com.gerenciamento.biblioteca_api.repositorios;

import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

  boolean existsByLivrosAndDataDevolucaoAfter(Livros livro, LocalDate dataDevolucao);
}
