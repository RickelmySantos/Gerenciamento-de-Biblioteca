package com.gerenciamento.biblioteca_api.mock.factory;

import com.gerenciamento.biblioteca_api.core.util.Random;
import com.gerenciamento.biblioteca_api.mock.EntidadeFactory;
import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;

public class EmprestimoFactory implements EntidadeFactory<Emprestimo> {

  public static final EmprestimoFactory instance = new EmprestimoFactory();

  @Override
  public Emprestimo create() {
    return Emprestimo.builder().dataEmprestimo(Random.nextLocalDate())
        .dataDevolucao(Random.nextLocalDate()).build();
  }

}
