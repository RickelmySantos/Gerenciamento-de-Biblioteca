package com.gerenciamento.biblioteca_api.controladores;

import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
import com.gerenciamento.biblioteca_api.servicos.EmprestimoService;
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
public class EmprestimoApi {

  private final EmprestimoService emprestimoService;

  @PostMapping
  public ResponseEntity<EmprestimoDto> cadastrar(@RequestBody @Valid EmprestimoDto emprestimoDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.emprestimoService.cadastrar(emprestimoDto));
  }

  @GetMapping
  public ResponseEntity<List<EmprestimoDto>> listAll() {
    return ResponseEntity.status(HttpStatus.OK).body(this.emprestimoService.listAll());
  }

  @GetMapping("/emprestimo/{id}")
  public ResponseEntity<EmprestimoDto> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.emprestimoService.buscarPorId(id));
  }

  @PutMapping("/emprestimo/{id}")
  public ResponseEntity<EmprestimoDto> atualizar(@PathVariable Long id,
      @RequestBody @Valid EmprestimoDto emprestimoDto) {
    return ResponseEntity.status(HttpStatus.OK).body(this.emprestimoService.atualizar(id, emprestimoDto));
  }

  @DeleteMapping("/emprestimo/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    this.emprestimoService.deletar(id);
    return ResponseEntity.noContent().build();
  }
}
