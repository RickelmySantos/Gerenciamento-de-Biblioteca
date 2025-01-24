package com.gerenciamento.biblioteca_api.controladores;

import com.gerenciamento.biblioteca_api.modelos.dtos.LivrosDto;
import com.gerenciamento.biblioteca_api.servicos.LivrosService;
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
@RequestMapping("/livros")
@Tag(name = "Livros", description = "APIs para gerenciamento de livros")
public class LivroApi {

  private final LivrosService livroService;

  @Operation(summary = "Cadastrar um livro", description = "Cadastra um livro na base de dados")
  @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso")
  @PostMapping
  public ResponseEntity<LivrosDto> cadastrar(@RequestBody @Valid LivrosDto livrosDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.livroService.cadastrar(livrosDto));
  }

  @Operation(summary = "Listar todos os livros",
      description = "Lista todos os livros cadastrados na base de dados")
  @ApiResponse(responseCode = "200", description = "Livros listados com sucesso")
  @GetMapping
  public ResponseEntity<List<LivrosDto>> listAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.livroService.listAll());
  }

  @Operation(summary = "Buscar livro por ID",
      description = "Busca um livro na base de dados por ID")
  @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso")
  @GetMapping("/livro/{id}")
  public ResponseEntity<LivrosDto> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.livroService.buscarPorId(id));
  }

  @Operation(summary = "Atualizar um livro", description = "Atualiza um livro na base de dados")
  @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso")
  @PutMapping("/atualizar")
  public ResponseEntity<LivrosDto> atualizar(@RequestBody @Valid LivrosDto livrosDto) {
    return ResponseEntity.ok(this.livroService.atualizar(livrosDto));
  }

  @Operation(summary = "Deletar um livro", description = "Deleta um livro na base de dados")
  @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso")
  @DeleteMapping("/livro/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    this.livroService.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
