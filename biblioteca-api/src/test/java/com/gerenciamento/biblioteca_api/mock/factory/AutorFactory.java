package com.gerenciamento.biblioteca_api.mock.factory;

import com.gerenciamento.biblioteca_api.core.util.Random;
import com.gerenciamento.biblioteca_api.mock.EntidadeFactory;
import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;

public class AutorFactory implements EntidadeFactory<Autor> {

  public static final AutorFactory instance = new AutorFactory();

  @Override
  public Autor create() {
    return Autor.builder().nome(Random.nextAlphabeticString(20))
        .sobrenome(Random.nextAlphabeticString(10)).nacionalidade(Random.nextAlphabeticString(10))
        .dataNascimento(Random.nextLocalDate()).build();
  }

  public Autor create(String nome, String sobrenome) {
    return Autor.builder().nome(nome).sobrenome(sobrenome).build();
  }

}
