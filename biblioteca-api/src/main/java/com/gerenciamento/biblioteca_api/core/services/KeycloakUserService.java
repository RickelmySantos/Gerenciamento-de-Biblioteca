package com.gerenciamento.biblioteca_api.core.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakUserService {


  private final String KEYCLOAK_USERS_URL =
      "http://localhost:8280/auth/admin/realms/REALM_LOCAL/users";


  public String buscarUsuariosKeycloak(String accessToken) {
    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + accessToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<String> response =
        rt.exchange(this.KEYCLOAK_USERS_URL, HttpMethod.GET, entity, String.class);

    return response.getBody();
  }
}
