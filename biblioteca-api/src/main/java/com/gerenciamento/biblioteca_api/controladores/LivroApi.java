package com.gerenciamento.biblioteca_api.controladores;

import com.gerenciamento.biblioteca_api.modelos.dtos.LivrosDto;
import com.gerenciamento.biblioteca_api.servicos.LivrosService;
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
@RequestMapping("/livros")
@Tag(name = "Livros", description = "APIs para gerenciamento de livros")
public class LivroApi {

  private final LivrosService livroService;

  @PostMapping
  public ResponseEntity<LivrosDto> cadastrar(@RequestBody @Valid LivrosDto livrosDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.livroService.cadastrar(livrosDto));
  }

  @GetMapping
  public ResponseEntity<List<LivrosDto>> listAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.livroService.listAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<LivrosDto> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.livroService.buscarPorId(id));
  }

  @PutMapping("/atualizar")
  public ResponseEntity<LivrosDto> atualizar(@RequestBody @Valid LivrosDto livrosDto) {
    return ResponseEntity.ok(this.livroService.atualizar(livrosDto));
  }

  @DeleteMapping("/livro/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    this.livroService.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
