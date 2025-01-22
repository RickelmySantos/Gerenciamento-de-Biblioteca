package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import com.gerenciamento.biblioteca_api.modelos.mappers.EmprestimoMapper;
import com.gerenciamento.biblioteca_api.repositorios.EmprestimoRepository;
import com.gerenciamento.biblioteca_api.repositorios.LivrosRepository;
import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmprestimoServiceTest {

  @InjectMocks
  private EmprestimoService emprestimoService;
  @Mock
  private EmprestimoMapper emprestimoMapper;
  @Mock
  private EmprestimoRepository emprestimoRepository;
  @Mock
  private UsuarioRepository usuarioRepository;
  @Mock
  private LivrosRepository livroRepository;


  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /* SALVAR */

  @Test
  void dadaEntidadeValida_QuandoChamarSalvar_EntaoDeveSalvar() {

    // Given
    EmprestimoDto emprestimoDto = new EmprestimoDto();
    emprestimoDto.setUsuarioId(1L);
    emprestimoDto.setLivroId(1L);
    emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-10"));
    emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-20"));

    Usuario usuario = new Usuario();
    usuario.setId(1L);

    Livros livros = new Livros();
    livros.setId(1L);

    Emprestimo emprestimo = new Emprestimo();
    emprestimo.setUsuario(usuario);
    emprestimo.setLivros(livros);
    emprestimo.setDataEmprestimo(LocalDate.parse("2025-10-10"));
    emprestimo.setDataDevolucao(LocalDate.parse("2025-10-20"));

    Emprestimo savedEmprestimo = new Emprestimo();
    savedEmprestimo.setId(1L);
    savedEmprestimo.setUsuario(usuario);
    savedEmprestimo.setLivros(livros);
    savedEmprestimo.setDataEmprestimo(LocalDate.parse("2025-10-10"));
    savedEmprestimo.setDataDevolucao(LocalDate.parse("2025-10-20"));

    EmprestimoDto savedEmprestimoDto = new EmprestimoDto();
    savedEmprestimoDto.setId(1L);
    savedEmprestimoDto.setUsuarioId(1L);
    savedEmprestimoDto.setLivroId(1L);
    savedEmprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-10"));
    savedEmprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-20"));

    Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    Mockito.when(this.livroRepository.findById(1L)).thenReturn(Optional.of(livros));
    Mockito.when(this.emprestimoRepository.save(ArgumentMatchers.any(Emprestimo.class)))
        .thenReturn(savedEmprestimo);
    Mockito.when(this.emprestimoMapper.paraDto(ArgumentMatchers.any(Emprestimo.class)))
        .thenReturn(savedEmprestimoDto);

    // When
    EmprestimoDto result = this.emprestimoService.salvar(emprestimoDto);

    // Then
    Assertions.assertEquals(savedEmprestimoDto, result);
  }

  @Test
  void dadoUsuarioNaoEncontrado_QuandoChamarSalvar_EntaoRetornarIllegalArgumentException() {

    // Given
    EmprestimoDto emprestimoDto = new EmprestimoDto();
    emprestimoDto.setUsuarioId(1L);
    emprestimoDto.setLivroId(1L);
    emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-10"));
    emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-20"));

    // When & Then
    Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.emprestimoService.salvar(emprestimoDto);
    });
  }

  @Test
  void dadoLivroNaoEncontrado_QuandoChamarSalvar_EntaoRetornarIllegalArgumentException() {

    // Given
    EmprestimoDto emprestimoDto = new EmprestimoDto();
    emprestimoDto.setUsuarioId(1L);
    emprestimoDto.setLivroId(1L);
    emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-10"));
    emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-20"));

    Usuario usuario = new Usuario();
    usuario.setId(1L);

    // When & Then
    Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    Mockito.when(this.livroRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.emprestimoService.salvar(emprestimoDto);
    });
  }
}
