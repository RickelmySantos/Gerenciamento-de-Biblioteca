package com.gerenciamento.biblioteca_api.mock.factory;

import com.gerenciamento.biblioteca_api.core.util.Random;
import com.gerenciamento.biblioteca_api.mock.EntidadeFactory;
import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import com.gerenciamento.biblioteca_api.modelos.enums.TipoUsuario;

public class UsuarioFactory implements EntidadeFactory<Usuario> {

  public static UsuarioFactory instance = new UsuarioFactory();

  @Override
  public Usuario create() {

    return Usuario.builder().nome(Random.nextAlphabeticString(10))
        .email(Random.nextAlphabeticString(10)).senha(Random.nextAlphabeticString(10))
        .tipoUsuario(Random.nextElement(TipoUsuario.values()))
        .emprestimo(EmprestimoFactory.instance.create(1)).build();
  }


}
