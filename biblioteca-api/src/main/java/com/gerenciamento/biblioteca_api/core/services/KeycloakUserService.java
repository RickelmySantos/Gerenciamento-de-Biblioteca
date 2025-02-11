package com.gerenciamento.biblioteca_api.core.services;

import java.util.List;
import java.util.stream.Collectors;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// @Service
// public class KeycloakUserService {


// private final Keycloak keycloak;

// @Value("${keycloak.realm}")
// private String realm;

// public KeycloakUserService(@Value("${keycloak.auth-server-url}") String authServerUrl,
// @Value("${keycloak.realm}") String realm, @Value("${keycloak.client-id}") String clientId,
// @Value("${keycloak.client-secret}") String clientSecret) {
// this.keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl).realm(realm)
// .clientId(clientId).clientSecret(clientSecret).build();
// }

// public List<String> buscarUsuariosKeycloack() {
// UsersResource usersResource = this.keycloak.realm(this.realm).users();
// List<UserRepresentation> users = usersResource.list();
// return users.stream().map(UserRepresentation::getUsername).collect(Collectors.toList());
// }
// }
