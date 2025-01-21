package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.mock.factory.LivrosFactory;
import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
import com.gerenciamento.biblioteca_api.modelos.dtos.LivrosDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.mappers.LivrosMapper;
import com.gerenciamento.biblioteca_api.repositorios.LivrosRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LivrosServiceTest {
  @InjectMocks
  protected LivrosService service;
  @Mock
  protected LivrosRepository repository;

  @Mock
  protected LivrosMapper mapper;


  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  /* SALVAR */

  @Test
  void dadoEntidadeValida_QuandoCadastrar_EntaoRetornarEntidadeCadastrada() {

    Long id = 1L;
    // Given
    Livros livros = LivrosFactory.instance.create();
    livros.setId(id);



    LivrosDto livrosDto = new LivrosDto();
    livrosDto.setTitulo(livros.getTitulo());
    livrosDto.setIdioma(livros.getIdioma());
    livrosDto.setGenero(livros.getGenero());
    livrosDto.setEditora(livros.getEditora());
    livrosDto.setAutor(livros.getAutor());
    livrosDto.setEmprestimo(livros.getEmprestimo().stream().map(emprestimo -> {
      EmprestimoDto dto = new EmprestimoDto();

      dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
      dto.setDataDevolucao(emprestimo.getDataDevolucao());
      return dto;
    }).collect(Collectors.toList()));


    // When
    Mockito.when(this.repository.save(livros)).thenReturn(livros);
    Mockito.when(this.mapper.paraEntidade(livrosDto)).thenReturn(livros);
    Mockito.when(this.mapper.paraDto(livros)).thenReturn(livrosDto);

    LivrosDto result = this.service.salvar(livrosDto);

    // Then
    Assertions.assertNotNull(result);
    Assertions.assertEquals(livrosDto, result);
    Mockito.verify(this.repository, Mockito.times(1)).save(livros);
    Mockito.verify(this.mapper, Mockito.times(1)).paraEntidade(livrosDto);
    Mockito.verify(this.mapper, Mockito.times(1)).paraDto(livros);
  }

  @Test
  void dadaEntidadeInvalida_QuandoCadastrar_EntaoRetornarIllegalArgumentException() {
    Long id = 99L;

    // Given
    LivrosDto livrosDto = new LivrosDto();
    livrosDto.setId(id);
    // When

    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.service.salvar(livrosDto);
    });

    // Then

    Assertions.assertEquals("Id deve ser nulo", ex.getMessage());
    Mockito.verifyNoInteractions(this.repository);
    Mockito.verifyNoInteractions(this.mapper);
  }

  @Test
  void dadaEntidadeNula_EntaoRetornarIllegalArgumentException() {
    // Given
    LivrosDto livrosDto = null;
    // When

    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.service.salvar(livrosDto);
    });

    // Then

    Assertions.assertEquals("Livro não pode ser nulo", ex.getMessage());
    Mockito.verifyNoInteractions(this.repository);
    Mockito.verifyNoInteractions(this.mapper);
  }

  /* ATUALIZAR */

  @Test
  void dadaEntidadeValida_QuandoChamarAtualizar_EntaoRetornarEntidadeAtualizada() {
    Long id = 1L;

    // Given

    Livros livros = LivrosFactory.instance.create("Xpto", "Portugues");
    livros.setId(id);

    LivrosDto livrosDto = new LivrosDto();
    livrosDto.setId(id);
    livrosDto.setTitulo("Xpto");
    livrosDto.setEditora("Ingles");

    // When
    Mockito.when(this.repository.findById(id)).thenReturn(Optional.of(livros));
    Mockito.when(this.repository.save(livros)).thenReturn(livros);
    Mockito.when(this.mapper.paraEntidade(livrosDto)).thenReturn(livros);
    Mockito.when(this.mapper.paraDto(livros)).thenReturn(livrosDto);

    LivrosDto result = this.service.atualizar(livrosDto);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(livrosDto, result);
    Assertions.assertEquals(livrosDto.getTitulo(), result.getTitulo());
    Assertions.assertEquals(livrosDto.getIdioma(), result.getIdioma());
    Mockito.verify(this.repository, Mockito.times(1)).findById(livros.getId());
    Mockito.verify(this.mapper, Mockito.times(1)).paraEntidade(livrosDto);
    Mockito.verify(this.mapper, Mockito.times(1)).paraDto(livros);
  }

  @Test
  void dadaEntidadeNaoCadastrada_QuandoChamarAtualizar_EntaoRetornarIllegalArgumentException() {

    Long id = 99L;

    // Given
    LivrosDto livrosDto = new LivrosDto();
    livrosDto.setId(id);

    // When
    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.service.atualizar(livrosDto);
    });

    // Then
    Assertions.assertEquals("Livro não encontrado", ex.getMessage());
    Mockito.verify(this.repository, Mockito.times(1)).findById(id);
    Mockito.verifyNoInteractions(this.mapper);
  }

  @Test
  void dadaEntidadeInvalida_QuandoChamarAtualizar_EntaoRetornarIllegalArgumentException() {

    // Given
    LivrosDto livrosDto = new LivrosDto();
    livrosDto.setId(null);

    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.service.atualizar(livrosDto);
    });

    // Then
    Assertions.assertEquals("Id não pode ser nulo", ex.getMessage());
    Mockito.verifyNoInteractions(this.repository);
    Mockito.verifyNoInteractions(this.mapper);
  }

  @Test
  void dadaEntidadeExistente_QuandaChamarDeletar_EntaoDeletarEntidade() {

    Long id = 1L;

    // Given
    Livros livros = LivrosFactory.instance.create("Xpto", "Portugues");
    livros.setId(id);

    // When
    Mockito.when(this.repository.findById(livros.getId())).thenReturn(Optional.of(livros));
    Mockito.doNothing().when(this.repository).deleteById(id);

    this.service.deletar(id);

    // Then

    Mockito.verify(this.repository, Mockito.times(1)).findById(id);
    Mockito.verify(this.repository, Mockito.times(1)).deleteById(id);
  }

  @Test
  void dadaEntidadeNaoExistente_QuandaChamarDeletar_EntaoRetornarIllegalArgumentException() {

    Long id = 99L;

    // Given
    // When
    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.service.deletar(id);
    });

    // Then
    Assertions.assertEquals("não foi possível encontrar o livro com o id informado",
        ex.getMessage());
    Mockito.verify(this.repository, Mockito.times(1)).findById(id);
    Mockito.verifyNoMoreInteractions(this.repository);
  }
}

