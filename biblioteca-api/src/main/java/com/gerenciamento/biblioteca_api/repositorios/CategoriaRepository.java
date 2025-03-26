package com.gerenciamento.biblioteca_api.repositorios;

import com.gerenciamento.biblioteca_api.modelos.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
