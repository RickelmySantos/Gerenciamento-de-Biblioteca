package com.gerenciamento.biblioteca_api.mock;

import com.gerenciamento.biblioteca_api.core.util.Random;
import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;

public class EntidadeFactory {

  public final static EntidadeFactory instance = new EntidadeFactory();

  public Autor create() {
    return Autor.builder().nome(Random.nextAlphabeticString(20))
        .sobrenome(Random.nextAlphabeticString(10)).nacionalidade(Random.nextAlphabeticString(10))
        .dataNascimento(Random.nexLocalDate()).build();
  }

  public Autor create(String nome, String sobrenome) {
    return Autor.builder().nome(nome).sobrenome(sobrenome)
        .nacionalidade(Random.nextAlphabeticString(10)).dataNascimento(Random.nexLocalDate())
        .build();
  }

}
