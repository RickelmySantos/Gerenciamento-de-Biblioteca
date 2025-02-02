package com.gerenciamento.biblioteca_api.core.servicos;

import com.gerenciamento.biblioteca_api.core.modelo.EntidadeBase;
import com.gerenciamento.biblioteca_api.core.repositorios.BaseRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(propagation = Propagation.REQUIRED)
public interface QueryService<E extends EntidadeBase, R extends BaseRepository<E>>
    extends BaseService<R> {

  default long total() {
    return this.getRepository().count();
  }

  default Optional<E> buscarPorId(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");
    Assert.isTrue(id > 0, "Id deve ser maior que 0");

    return this.getRepository().findById(id);
  }

  default Page<E> listar() {
    return this.getRepository().findAll(Pageable.unpaged());
  }

  default Page<E> listar(Pageable pageable) {
    Assert.notNull(pageable, "Pageable não pode ser nulo");

    return this.getRepository().findAll(pageable);
  }
}
