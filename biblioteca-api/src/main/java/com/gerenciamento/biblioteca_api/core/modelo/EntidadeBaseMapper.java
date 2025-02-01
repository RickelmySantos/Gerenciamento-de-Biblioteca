package com.gerenciamento.biblioteca_api.core.modelo;

import java.util.List;
import org.mapstruct.IterableMapping;

public interface EntidadeBaseMapper<E extends EntidadeBase, D extends EntidadeBaseDTO<? extends E>> {

  public static final String PARA_DTO_EM_LISTA = "paraDTOemLista";

  E paraEntidade(D dto);

  D paraDTO(E entidade);

  List<E> paraEntidades(List<D> dto);

  @IterableMapping(qualifiedByName = EntidadeBaseMapper.PARA_DTO_EM_LISTA)
  List<D> paraDTOs(List<E> entidade);
}
