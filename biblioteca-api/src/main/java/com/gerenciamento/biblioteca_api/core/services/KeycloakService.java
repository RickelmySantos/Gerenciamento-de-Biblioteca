package com.gerenciamento.biblioteca_api.core.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;


@Service
public class KeycloakService {
  private final String KEYCLOAK_URL = "http://localhost:8280/auth/admin/realms/REALM_LOCAL/users/";


  private final RestClient restClient;
  private final ObjectMapper objectMapper;


  private final UserAutenticadoService userAutenticadoService;

  public KeycloakService(RestClient restClient, ObjectMapper objectMapper,
      UserAutenticadoService userAutenticadoService) {
    this.restClient = restClient;
    this.objectMapper = objectMapper;

    this.userAutenticadoService = userAutenticadoService;
  }

  public JsonNode getUserFromKeycloak(String accesToken) {

    String userId = this.userAutenticadoService.getUserId();

    try {
      String responseBody = this.restClient.get().uri(this.KEYCLOAK_URL + userId)
          .header("Authorization", "Bearer " + accesToken).accept(MediaType.APPLICATION_JSON)
          .retrieve().onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
              (request, response) -> {
                throw new RestClientException("Erro ao buscar usuário no Keycloak");
              })
          .body(String.class);
      return this.objectMapper.readTree(responseBody);
    } catch (HttpClientErrorException e) {
      throw new RuntimeException("Erro ao buscar usuário no Keycloak");
    } catch (RestClientResponseException e) {
      throw new RuntimeException("Erro ao buscar usuário no Keycloak");
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar usuário no Keycloak");
    }
  }
}
