package com.gerenciamento.biblioteca_api.controladores;

import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
import com.gerenciamento.biblioteca_api.servicos.EmprestimoService;
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
@RequestMapping("/emprestimos")
@Tag(name = "Emprestimo", description = "Controlador de emprestimos")
public class EmprestimoApi {

  private final EmprestimoService emprestimoService;

  @Operation(summary = "Cadastrar um emprestimo", method = "POST")
  @ApiResponse(responseCode = "201", description = "Emprestimo cadastrado com sucesso")
  @PostMapping
  public ResponseEntity<EmprestimoDto> cadastrar(@RequestBody @Valid EmprestimoDto emprestimoDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(this.emprestimoService.cadastrar(emprestimoDto));
  }

  @Operation(summary = "Listar todos os emprestimos", method = "GET")
  @ApiResponse(responseCode = "200", description = "Emprestimos listados com sucesso")
  @GetMapping
  public ResponseEntity<List<EmprestimoDto>> listAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.emprestimoService.listAll());
  }

  @Operation(summary = "Buscar emprestimo por id", method = "GET")
  @ApiResponse(responseCode = "200", description = "Emprestimo encontrado com sucesso")
  @GetMapping("/emprestimo/{id}")
  public ResponseEntity<EmprestimoDto> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.emprestimoService.buscarPorId(id));
  }

  @Operation(summary = "Atualizar emprestimo", method = "PUT")
  @ApiResponse(responseCode = "201", description = "Emprestimo atualizado com sucesso")
  @PutMapping("/emprestimo/{id}")
  public ResponseEntity<EmprestimoDto> atualizar(@PathVariable Long id,
      @RequestBody @Valid EmprestimoDto emprestimoDto) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.emprestimoService.atualizar(id, emprestimoDto));
  }

  @Operation(summary = "Deletar emprestimo", method = "DELETE")
  @ApiResponse(responseCode = "204", description = "Emprestimo deletado com sucesso")
  @DeleteMapping("/emprestimo/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    this.emprestimoService.deletar(id);
    return ResponseEntity.noContent().build();
  }
}
