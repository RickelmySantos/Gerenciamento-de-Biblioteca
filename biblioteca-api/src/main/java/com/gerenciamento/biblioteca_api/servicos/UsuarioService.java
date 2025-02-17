package com.gerenciamento.biblioteca_api.servicos;

import com.fasterxml.jackson.databind.JsonNode;
import com.gerenciamento.biblioteca_api.core.services.KeycloakService;
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

  private final KeycloakService keycloakService;

  public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper,
      KeycloakService keycloakService) {
    this.repository = repository;
    this.mapper = mapper;
    this.keycloakService = keycloakService;

  }

  /**
   * Retorna o usuário autenticado com base no token JWT.
   */
  public UsuarioDto buscarUsuarioAutenticado(String accessToken) {
    if (accessToken == null || accessToken.trim().isEmpty()) {
      throw new IllegalArgumentException("Token de acesso não pode ser nulo ou vazio.");
    }

    JsonNode userNode = this.keycloakService.getUserFromKeycloak(accessToken);

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
