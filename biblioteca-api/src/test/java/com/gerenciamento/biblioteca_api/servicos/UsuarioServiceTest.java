package com.gerenciamento.biblioteca_api.servicos;

import com.fasterxml.jackson.databind.JsonNode;
import com.gerenciamento.biblioteca_api.core.services.KeycloakService;
import com.gerenciamento.biblioteca_api.mock.factory.KeycloakFactory;
import com.gerenciamento.biblioteca_api.modelos.dtos.UsuarioDto;
import com.gerenciamento.biblioteca_api.modelos.mappers.UsuarioMapper;
import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
  private static final String USUARIO_ID = "123";
  private static final String ACCESS_TOKEN = "mockedToken";

  @InjectMocks
  private UsuarioService usuarioService;
  @Mock
  private UsuarioRepository usuarioRepository;
  @Mock
  private UsuarioMapper usuarioMapper;
  @Mock
  private KeycloakService keycloakService;



  private JsonNode keycloakUserNode;

  @BeforeEach
  void setUp() {

    this.keycloakUserNode = KeycloakFactory.JsonNodeUsuario(UsuarioServiceTest.USUARIO_ID,
        "Usuario", "xpto", "teste@gmail.com");
  }


  @Test
  void dadaEntidadeValida_QuandoChamarMetodoBuscarUsuarioAutenticado_EntaoDeveRetornarEntidade() {

    Mockito.when(this.keycloakService.getUserFromKeycloak(UsuarioServiceTest.ACCESS_TOKEN))
        .thenReturn(this.keycloakUserNode);

    UsuarioDto usuarioAuth =
        this.usuarioService.buscarUsuarioAutenticado(UsuarioServiceTest.ACCESS_TOKEN);

    Assertions.assertNotNull(usuarioAuth);
    Assertions.assertEquals(UsuarioServiceTest.USUARIO_ID, usuarioAuth.getId());
    Assertions.assertEquals("Usuario xpto", usuarioAuth.getNome());
    Assertions.assertEquals("teste@gmail.com", usuarioAuth.getEmail());

    Mockito.verify(this.keycloakService).getUserFromKeycloak(UsuarioServiceTest.ACCESS_TOKEN);

  }

  @Test
  void dadaEntidadeComSobreNomeAusente_QuandoChamarMetodoBuscarUsuarioAutenticado_EntaoDeveRetornarEntidade() {

    Mockito.when(this.keycloakService.getUserFromKeycloak(UsuarioServiceTest.ACCESS_TOKEN))
        .thenReturn(this.keycloakUserNode);

    UsuarioDto usuarioAuth =
        this.usuarioService.buscarUsuarioAutenticado(UsuarioServiceTest.ACCESS_TOKEN);

    Assertions.assertNotNull(usuarioAuth);
    Assertions.assertEquals(UsuarioServiceTest.USUARIO_ID, usuarioAuth.getId());
    Assertions.assertEquals("Usuario ", usuarioAuth.getNome());
    Assertions.assertEquals("teste@gmail.com", usuarioAuth.getEmail());

  }

  @Test
  void dadoTokenInvalido_QuandoChamarMetodoBuscarUsuarioAutenticado_EntaoDeveLancarExcecao() {

    Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.usuarioService.buscarUsuarioAutenticado(null);
    });
    Assertions.assertEquals("Token de acesso não pode ser nulo ou vazio.", exception.getMessage());
  }


}
