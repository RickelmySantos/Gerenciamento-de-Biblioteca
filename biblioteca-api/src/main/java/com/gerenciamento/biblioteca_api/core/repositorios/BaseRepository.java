package com.gerenciamento.biblioteca_api.core.repositorios;

import com.gerenciamento.biblioteca_api.core.modelo.EntidadeBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
public interface BaseRepository<E extends EntidadeBase>
    extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

}
