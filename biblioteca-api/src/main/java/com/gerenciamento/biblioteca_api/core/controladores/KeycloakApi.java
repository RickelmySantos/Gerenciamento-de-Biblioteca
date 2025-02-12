package com.gerenciamento.biblioteca_api.core.controladores;

import com.gerenciamento.biblioteca_api.core.services.KeycloakUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/keycloak")
@Tag(name = "Keycloak", description = "Controlador do Keycloak")
@RestController
public class KeycloakApi {
  private final KeycloakUserService keycloakUserService;

  @GetMapping("/users")
  public String buscarUsuariosKeycloak(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

    String acessToken = token.replace("Bearer ", "").trim();

    return this.keycloakUserService.buscarUsuariosKeycloak(acessToken);
  }

}

