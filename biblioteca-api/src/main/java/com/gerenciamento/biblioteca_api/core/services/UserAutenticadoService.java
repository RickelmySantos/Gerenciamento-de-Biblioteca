package com.gerenciamento.biblioteca_api.core.services;

import com.gerenciamento.biblioteca_api.modelos.enums.TipoUsuario;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserAutenticadoService {
  public String getUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication.getPrincipal() instanceof Jwt jwt) {
      return jwt.getClaim("sub");
    } else if (authentication.getPrincipal() instanceof String userId) {
      return userId;
    } else {
      throw new IllegalStateException("Usuário não autenticado ou formato do token inválido");
    }
  }

  public String getUserName() {
    return this.getClaimFromToken("preferred_username");
  }

  public String getEmail() {
    return this.getClaimFromToken("email");
  }

  public TipoUsuario determinarTipoUsuario(Jwt jwt) {
    return Optional.ofNullable(jwt.getClaim("resource_access"))
        .filter(resourceAccess -> resourceAccess instanceof Map)
        .map(resourceAccess -> (Map<String, Object>) resourceAccess)
        .map(map -> map.get("biblioteca-ui")).filter(bibliotecaUi -> bibliotecaUi instanceof Map)
        .map(bibliotecaUi -> (Map<String, Object>) bibliotecaUi)
        .map(bibliotecaUi -> bibliotecaUi.get("roles")).map(roles -> (List<String>) roles)
        .map(this::getTipoUsuario).orElse(TipoUsuario.USUARIO);
  }

  private String getClaimFromToken(String claim) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication.getPrincipal() instanceof Jwt jwt) {
      return jwt.getClaim(claim);
    } else {
      throw new IllegalStateException("Usuário não autenticado ou formato do token inválido");
    }
  }

  private TipoUsuario getTipoUsuario(List<String> roles) {
    if (roles.contains("ADMIN")) {
      return TipoUsuario.ADMINISTRADOR;
    }
    if (roles.contains("GESTOR")) {
      return TipoUsuario.GESTOR;
    }
    return TipoUsuario.USUARIO;
  }

}
