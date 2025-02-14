package com.gerenciamento.biblioteca_api.core.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciamento.biblioteca_api.modelos.enums.TipoUsuario;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAutenticadoService {

  private final String KEYCLOAK_URL = "http://localhost:8280/auth/admin/realms/REALM_LOCAL/users/";


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
    Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

    if (resourceAccess != null && resourceAccess.containsKey("biblioteca-ui")) {
      Map<String, Object> bibliotecaUiRoles =
          (Map<String, Object>) resourceAccess.get("biblioteca-ui");
      List<String> roles = (List<String>) bibliotecaUiRoles.get("roles");

      if (roles.contains("ADMIN")) {
        return TipoUsuario.ADMINISTRADOR;
      } else if (roles.contains("GESTOR")) {
        return TipoUsuario.GESTOR;
      }
    }
    return TipoUsuario.USUARIO;
  }

  public JsonNode getUserFromKeycloak(String acessToken) {
    String userId = this.getUserId();
    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + acessToken);
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<String> response =
        rt.exchange(this.KEYCLOAK_URL + userId, HttpMethod.GET, entity, String.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      try {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(response.getBody());
      } catch (Exception e) {
        throw new RuntimeException("Erro ao buscar usuário no Keycloak");
      }
    } else {
      throw new RuntimeException("Erro ao buscar usuário no Keycloak");
    }
  }

  private String getClaimFromToken(String claim) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication.getPrincipal() instanceof Jwt jwt) {
      return jwt.getClaim(claim);
    } else {
      throw new IllegalStateException("Usuário não autenticado ou formato do token inválido");
    }
  }

}
