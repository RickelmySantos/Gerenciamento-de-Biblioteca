package com.gerenciamento.biblioteca_api.controladores;

import com.gerenciamento.biblioteca_api.modelos.dtos.UsuarioDto;
import com.gerenciamento.biblioteca_api.servicos.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuário", description = "Controlador de Usuário")
public class UsuarioApi {

  private final UsuarioService usuarioService;



  @PostMapping
  public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(this.usuarioService.cadastrar(usuarioDto));
  }

  @GetMapping()
  public ResponseEntity<List<UsuarioDto>> listAll() {
    return ResponseEntity.ok(this.usuarioService.listAll());
  }


  @GetMapping("/{id}")
  public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(this.usuarioService.buscarPorId(id));
  }

  @PutMapping
  public ResponseEntity<UsuarioDto> atualizar(@RequestBody @Valid UsuarioDto usuarioDto) {
    return ResponseEntity.ok(this.usuarioService.atualizar(usuarioDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    this.usuarioService.deletar(id);
    return ResponseEntity.noContent().build();
  }


}
