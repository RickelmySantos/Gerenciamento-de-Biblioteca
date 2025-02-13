package com.gerenciamento.biblioteca_api.controladores;

import com.gerenciamento.biblioteca_api.modelos.dtos.UsuarioDto;
import com.gerenciamento.biblioteca_api.servicos.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuário", description = "Controlador de Usuário")
public class UsuarioApi {

  private final UsuarioService usuarioService;



  @GetMapping("/me")
  public ResponseEntity<UsuarioDto> buscar(@RequestHeader("Authorization") String token) {
    String accessToken = token.replace("Bearer ", "").trim();

    UsuarioDto usuarioDto = this.usuarioService.buscarUsuarioAutenticado(accessToken);

    return ResponseEntity.ok(usuarioDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable String id) {
    return ResponseEntity.ok(this.usuarioService.buscarPorId(id));
  }

  @GetMapping
  public ResponseEntity<List<UsuarioDto>> listAll() {
    return ResponseEntity.ok(this.usuarioService.listarTodos());
  }
}
