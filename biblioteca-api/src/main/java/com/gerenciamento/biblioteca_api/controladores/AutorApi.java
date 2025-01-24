package com.gerenciamento.biblioteca_api.controladores;

import com.gerenciamento.biblioteca_api.modelos.dtos.AutorDto;
import com.gerenciamento.biblioteca_api.servicos.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/autores")
@Tag(name = "Autor", description = "Controlador de autores")
public class AutorApi {

  private final AutorService autorService;

  @Operation(summary = "Cadastrar um autor", method = "POST")
  @ApiResponse(responseCode = "201", description = "Autor cadastrado com sucesso")
  @PostMapping
  public ResponseEntity<AutorDto> cadatrar(@RequestBody @Valid AutorDto autorDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.autorService.cadastrar(autorDto));
  }

  @Operation(summary = "Listar todos os autores", method = "GET")
  @ApiResponse(responseCode = "200", description = "Autores listados com sucesso")
  @GetMapping
  public ResponseEntity<List<AutorDto>> listAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.autorService.listAll());
  }

  @Operation(summary = "Buscar autor por id", method = "GET")
  @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso")
  @GetMapping("/autor/{id}")
  public ResponseEntity<AutorDto> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.autorService.buscarPorId(id));
  }

  @Operation(summary = "Atualizar autor", method = "PUT")
  @ApiResponse(responseCode = "201", description = "Autor atualizado com sucesso")
  @PutMapping
  public ResponseEntity<AutorDto> atualizar(@RequestBody @Valid AutorDto autorDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.autorService.atualizar(autorDto));
  }

  @Operation(summary = "Deletar autor", method = "DELETE")
  @ApiResponse(responseCode = "204", description = "Autor deletado com sucesso")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    this.autorService.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
