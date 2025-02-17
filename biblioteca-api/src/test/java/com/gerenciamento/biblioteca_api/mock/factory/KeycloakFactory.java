package com.gerenciamento.biblioteca_api.mock.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class KeycloakFactory {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private KeycloakFactory() {

  }

  public static JsonNode JsonNodeUsuario(String id, String firstName, String lastName,
      String email) {

    ObjectNode node = KeycloakFactory.objectMapper.createObjectNode();
    node.put("id", id);
    node.put("firstName", firstName);
    node.put("lastName", lastName);
    node.put("email", email);
    return node;

  }
}
