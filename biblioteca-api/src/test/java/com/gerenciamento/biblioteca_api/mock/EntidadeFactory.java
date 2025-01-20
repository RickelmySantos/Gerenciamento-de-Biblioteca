package com.gerenciamento.biblioteca_api.mock;

import java.util.ArrayList;
import java.util.List;

public interface EntidadeFactory<E> {

  E create();

  default List<E> create(int total) {
    List<E> entidades = new ArrayList<>();

    for (int i = 0; i < total; i++) {
      entidades.add(this.create());
    }
    return entidades;
  }

}
