package com.gerenciamento.biblioteca_api.core.servicos;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.gerenciamento.biblioteca_api.core.modelo.EntidadeBase;
import com.gerenciamento.biblioteca_api.core.repositorios.BaseRepository;

@Transactional(propagation = Propagation.SUPPORTS)
public interface BaseService<R extends BaseRepository<? extends EntidadeBase>> {

  R getRepository();
}
