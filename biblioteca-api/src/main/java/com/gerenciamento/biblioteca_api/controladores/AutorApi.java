package com.gerenciamento.biblioteca_api.controladores;

import com.gerenciamento.biblioteca_api.modelos.dtos.AutorDto;
import com.gerenciamento.biblioteca_api.servicos.AutorService;
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
public class AutorApi {

  private final AutorService autorService;

  @PostMapping
  public ResponseEntity<AutorDto> cadatrar(@RequestBody @Valid AutorDto autorDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.autorService.cadastrar(autorDto));
  }

  @GetMapping
  public ResponseEntity<List<AutorDto>> listAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.autorService.listAll());
  }

  @GetMapping("/autor/{id}")
  public ResponseEntity<AutorDto> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.autorService.buscarPorId(id));
  }

  @PutMapping
  public ResponseEntity<AutorDto> atualizar(@RequestBody @Valid AutorDto autorDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.autorService.atualizar(autorDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    this.autorService.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
