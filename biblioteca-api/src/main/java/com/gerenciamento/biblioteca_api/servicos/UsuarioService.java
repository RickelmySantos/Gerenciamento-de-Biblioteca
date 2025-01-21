package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.modelos.dtos.UsuarioDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import com.gerenciamento.biblioteca_api.modelos.mappers.UsuarioMapper;
import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
import org.springframework.util.Assert;

public class UsuarioService {

  private UsuarioRepository repository;

  private UsuarioMapper mapper;

  public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public UsuarioDto salvar(UsuarioDto usuarioDto) {
    Assert.notNull(usuarioDto, "Usuario não pode ser nulo");
    Assert.isNull(usuarioDto.getId(), "Id deve ser nulo");

    Usuario usuario = this.repository.save(this.mapper.paraEntidade(usuarioDto));

    return this.mapper.paraDto(usuario);
  }

  public UsuarioDto atualizar(UsuarioDto usuarioDto) {

    Assert.notNull(usuarioDto, "Usuario não pode ser nulo");
    Assert.notNull(usuarioDto.getId(), "Id não pode ser nulo");

    Usuario usuario = this.repository.findById(usuarioDto.getId())
        .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

    Usuario usuarioAtualizado = this.mapper.paraEntidade(usuarioDto);
    usuarioAtualizado.setId(usuario.getId());

    return this.mapper.paraDto(this.repository.save(usuarioAtualizado));
  }

  public UsuarioDto buscarPorId(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");

    Usuario usuario = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

    return this.mapper.paraDto(usuario);
  }

  public void deletar(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");

    Usuario usuario = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

    this.repository.delete(usuario);
  }

}
