package com.gerenciamento.biblioteca_api.mock.factory;

import com.gerenciamento.biblioteca_api.core.util.Random;
import com.gerenciamento.biblioteca_api.mock.EntidadeFactory;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;

public class LivrosFactory implements EntidadeFactory<Livros> {

  public static final LivrosFactory instance = new LivrosFactory();

  @Override
  public Livros create() {
    return Livros.builder().titulo(Random.nextAlphabeticString(10))
        .editora(Random.nextAlphabeticString(10)).idioma(Random.nextAlphabeticString(10))
        .genero(Random.nextAlphabeticString(10)).autor(AutorFactory.instance.create())
        .emprestimo(EmprestimoFactory.instance.create(1)).build();
  }

  public Livros create(String titulo, String editora) {
    return Livros.builder().titulo(titulo).editora(editora).build();
  }

}
