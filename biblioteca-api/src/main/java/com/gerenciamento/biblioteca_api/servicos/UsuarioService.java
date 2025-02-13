package com.gerenciamento.biblioteca_api.servicos;

import com.fasterxml.jackson.databind.JsonNode;
import com.gerenciamento.biblioteca_api.core.services.UserAutenticadoService;
import com.gerenciamento.biblioteca_api.modelos.dtos.UsuarioDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import com.gerenciamento.biblioteca_api.modelos.mappers.UsuarioMapper;
import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private final UsuarioRepository repository;

  private final UsuarioMapper mapper;

  private final UserAutenticadoService userAutenticadoService;

  public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper,
      UserAutenticadoService userAutenticadoService) {
    this.repository = repository;
    this.mapper = mapper;
    this.userAutenticadoService = userAutenticadoService;
  }

  // public UsuarioDto cadastrar(UsuarioDto usuarioDto) {
  // Assert.notNull(usuarioDto, "Usuario não pode ser nulo");
  // Assert.isNull(usuarioDto.getId(), "Id deve ser nulo");

  // Usuario usuario = this.repository.save(this.mapper.paraEntidade(usuarioDto));

  // return this.mapper.paraDto(usuario);
  // }

  // public UsuarioDto atualizar(UsuarioDto usuarioDto) {

  // Assert.notNull(usuarioDto, "Usuario não pode ser nulo");
  // Assert.notNull(usuarioDto.getId(), "Id não pode ser nulo");

  // Usuario usuario = this.repository.findById(usuarioDto.getId())
  // .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

  // Usuario usuarioAtualizado = this.mapper.paraEntidade(usuarioDto);
  // usuarioAtualizado.setId(usuario.getId());

  // return this.mapper.paraDto(this.repository.save(usuarioAtualizado));
  // }

  // public List<UsuarioDto> listAll() {
  // return this.repository.findAll().stream().map(this.mapper::paraDto).toList();
  // }

  // public UsuarioDto buscarPorId(Long id) {
  // Assert.notNull(id, "Id não pode ser nulo");

  // Usuario usuario = this.repository.findById(id)
  // .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

  // return this.mapper.paraDto(usuario);
  // }

  // public void deletar(Long id) {
  // Assert.notNull(id, "Id não pode ser nulo");

  // Usuario usuario = this.repository.findById(id)
  // .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

  // this.repository.delete(usuario);
  // }

  /**
   * Retorna o usuário autenticado com base no token JWT.
   */
  public UsuarioDto buscarUsuarioAutenticado(String accessToken) {
    JsonNode userNode = this.userAutenticadoService.getUserFromKeycloak(accessToken);

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId(userNode.get("id").asText());
    usuarioDto
        .setNome(userNode.get("firstName").asText() + " " + userNode.get("lastName").asText());
    usuarioDto.setEmail(userNode.get("email").asText());

    return usuarioDto;

  }

  /**
   * Busca um usuário por id.
   */

  public UsuarioDto buscarPorId(String id) {
    Usuario usuario = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

    return this.mapper.paraDto(usuario);
  }

  /**
   * Lista todos os usuários.
   */
  public List<UsuarioDto> listarTodos() {
    return this.repository.findAll().stream().map(this.mapper::paraDto).toList();
  }
}
