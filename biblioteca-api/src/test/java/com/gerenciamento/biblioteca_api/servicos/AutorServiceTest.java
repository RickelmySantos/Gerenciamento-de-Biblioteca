package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.mock.factory.AutorFactory;
import com.gerenciamento.biblioteca_api.modelos.dtos.AutorDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;
import com.gerenciamento.biblioteca_api.modelos.mappers.AutorMapper;
import com.gerenciamento.biblioteca_api.repositorios.AutorRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {

  @InjectMocks
  private AutorService autorService;
  @Mock
  private AutorRepository repository;
  @Mock
  private AutorMapper mapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /* LISTAR */

  @Test
  void quandoChamarListAll_EntaoRetornarListaDeAutores() {
    // Arrange
    Autor autor = AutorFactory.instance.create();

    AutorDto autorDto = new AutorDto();

    autorDto.setId(autor.getId());
    autorDto.setNome(autor.getNome());
    autorDto.setSobrenome(autor.getSobrenome());
    autorDto.setNacionalidade(autor.getNacionalidade());
    autorDto.setDataNascimento(autor.getDataNascimento());

    Mockito.when(this.repository.findAll()).thenReturn(List.of(autor));
    Mockito.when(this.mapper.paraDto(autor)).thenReturn(autorDto);

    // Act
    List<AutorDto> result = this.autorService.listAll();

    // Assert
    Assertions.assertNotNull(result);
    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals(autorDto, result.get(0));
    Mockito.verify(this.repository, Mockito.times(1)).findAll();
    Mockito.verify(this.mapper, Mockito.times(1)).paraDto(autor);

  }

  @Test
  void quandoChamarListAllVazia_EntaoRetornarListaVazia() {
    // Arrange

    Mockito.when(this.repository.findAll()).thenReturn(List.of());

    // Act
    List<AutorDto> result = this.autorService.listAll();

    // Assert
    Assertions.assertNotNull(result);
    Assertions.assertTrue(result.isEmpty());
    Mockito.verify(this.repository, Mockito.times(1)).findAll();

  }

  /* BUSCAR_POR_ID */

  @Test
  void quandoChamarBuscarPorId_EntaoRetornarAutor() {
    Long id = 1L;

    // Arrange
    Autor autor = AutorFactory.instance.create("Autor", "Xpto");
    autor.setId(id);
    AutorDto autorDto = new AutorDto();
    autorDto.setId(autor.getId());
    autorDto.setNome(autor.getNome());
    autorDto.setSobrenome(autor.getSobrenome());

    Mockito.when(this.repository.findById(autor.getId())).thenReturn(Optional.of(autor));
    Mockito.when(this.mapper.paraDto(autor)).thenReturn(autorDto);

    // Act

    AutorDto result = this.autorService.buscarPorId(autor.getId());

    // Assert

    Assertions.assertNotNull(result);
    Assertions.assertEquals(autorDto, result);
    Assertions.assertEquals(autorDto.getNome(), result.getNome());
    Assertions.assertEquals(autorDto.getSobrenome(), result.getSobrenome());
    Mockito.verify(this.repository, Mockito.times(1)).findById(autor.getId());
    Mockito.verify(this.mapper, Mockito.times(1)).paraDto(autor);
  }

  @Test
  void quandoChamarBuscarPorIdNull_EntaoRetornarException() {
    Long id = null;

    // Arrange
    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.autorService.buscarPorId(id);
    });
    // Assert
    Assertions.assertEquals("Id não pode ser nulo", ex.getMessage());
    Mockito.verifyNoInteractions(this.repository);
    Mockito.verifyNoInteractions(this.mapper);

  }

  @Test
  void quandoChamarBuscarPorIdInvalido_EntaoRetornarIllegalArgumentException() {
    Long id = 99L;

    Mockito.when(this.repository.findById(id)).thenReturn(Optional.empty());

    Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.autorService.buscarPorId(id);
    });

    Assertions.assertEquals("Autor não encontrado", ex.getMessage());
    Mockito.verify(this.repository, Mockito.times(1)).findById(id);
    Mockito.verifyNoInteractions(this.mapper);
  }

  /* SALVAR */

  @Test
  void quandoChamarCadastrar_ComEntidadeValida_EntaoPersistirComSucesso() {
    Long id = 1L;
    // Arrange

    Autor autor = AutorFactory.instance.create();
    autor.setId(id);
    autor.setDataNascimento(
        LocalDate.parse("12/12/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));


    AutorDto autorDto = new AutorDto();
    autorDto.setNome(autor.getNome());
    autorDto.setSobrenome(autor.getSobrenome());
    autorDto.setNacionalidade(autor.getNacionalidade());
    autorDto.setDataNascimento(autor.getDataNascimento());

    Mockito.when(this.repository.save(autor)).thenReturn(autor);
    Mockito.when(this.mapper.paraEntidade(autorDto)).thenReturn(autor);
    Mockito.when(this.mapper.paraDto(autor)).thenReturn(autorDto);

    // Act
    AutorDto result = this.autorService.cadastrar(autorDto);

    // Assert
    Assertions.assertNotNull(result);
    Assertions.assertEquals(autorDto, result);
    Assertions.assertEquals(autorDto.getNome(), result.getNome());
    Assertions.assertEquals(autorDto.getSobrenome(), result.getSobrenome());
    Assertions.assertEquals(autorDto.getNacionalidade(), result.getNacionalidade());
    Assertions.assertEquals(autorDto.getDataNascimento(), result.getDataNascimento());
    Mockito.verify(this.repository, Mockito.times(1)).save(autor);
    Mockito.verify(this.mapper, Mockito.times(1)).paraEntidade(autorDto);
  }

  @Test
  void quandoChamarCadastrarComEntidadeNula_EntaoRetornarExcption() {

    // Arrange
    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.autorService.cadastrar(null);
    });

    // Assert
    Assertions.assertEquals("Autor não pode ser nulo", ex.getMessage());
    Mockito.verifyNoInteractions(this.repository);
    Mockito.verifyNoInteractions(this.mapper);
  }

  @Test
  void quandoChamarCadastrarComIdNaoNulo_EntaoRetornarException() {
    Long id = 1L;

    // Arrange
    AutorDto autorDto = new AutorDto();
    autorDto.setId(id);

    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.autorService.cadastrar(autorDto);
    });

    // Assert
    Assertions.assertEquals("Id deve ser nulo", ex.getMessage());
    Mockito.verifyNoInteractions(this.repository);
    Mockito.verifyNoInteractions(this.mapper);
  }

  @Test
  void quandoChamarCadastrarComDataInvalida_EntaoRetornarIllegalArgumentException() {

    // Arrange
    AutorDto autorDto = new AutorDto();
    autorDto.setDataNascimento(null);

    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.autorService.cadastrar(autorDto);
    });

    // Assert
    Assertions.assertEquals("Data de nascimento não pode ser nula", ex.getMessage());
  }

  // @Test
  // void quandoChamarCadastrar_ComFormatadoDeDataInvalida_EntaoRetornarIllegalArgumentException() {

  // // Arrange
  // AutorDto autorDto = new AutorDto();
  // autorDto.setDataNascimento(LocalDate.of(2021, 12, 12));

  // IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
  // this.autorService.cadastrar(autorDto);
  // });

  // // Assert
  // Assertions.assertEquals("Formato de data inválido. Use o formato dd/MM/yyyy", ex.getMessage());
  // }



  /* ATUALIZAR */

  @Test
  void quandoChamarAtualizar_ComEntidadeValida_EntaoAtualizarComSucesso() {
    Long id = 1L;

    Autor autor = AutorFactory.instance.create("Autor", "Xpto");
    autor.setId(id);

    AutorDto autorDto = new AutorDto();
    autorDto.setId(autor.getId());
    autorDto.setNome("Autor Atualizado");
    autorDto.setSobrenome("Xpto ");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    autorDto.setDataNascimento(LocalDate.parse("12/12/2021", formatter));

    Mockito.when(this.repository.findById(autor.getId())).thenReturn(Optional.of(autor));
    Mockito.when(this.repository.save(autor)).thenReturn(autor);
    Mockito.when(this.mapper.paraEntidade(autorDto)).thenReturn(autor);
    Mockito.when(this.mapper.paraDto(autor)).thenReturn(autorDto);

    // Act

    AutorDto result = this.autorService.atualizar(autorDto);

    // assert

    Assertions.assertNotNull(result);
    Assertions.assertNotNull(result.getId());
    Assertions.assertEquals(autorDto, result);
    Mockito.verify(this.repository, Mockito.times(1)).findById(autor.getId());
    Mockito.verify(this.repository, Mockito.times(1)).save(autor);
    Mockito.verify(this.mapper, Mockito.times(1)).paraEntidade(autorDto);
    Mockito.verify(this.mapper, Mockito.times(1)).paraDto(autor);
  }

  @Test
  void quandoChamarAtualizarComEntidadeNula_EntaoRetornarException() {

    // Arrange
    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.autorService.atualizar(null);
    });

    // Assert
    Assertions.assertEquals("Autor não pode ser nulo", ex.getMessage());
    Mockito.verifyNoInteractions(this.repository);
    Mockito.verifyNoInteractions(this.mapper);
  }

  @Test
  void quandoChamarAtualizar_ComIdInvalido_EntaoRetornarIllegalArgumentExcption() {
    Long id = 99L;

    // Arrange
    AutorDto autorDto = new AutorDto();
    autorDto.setId(id);

    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.autorService.atualizar(autorDto);
    });

    // Assert
    Assertions.assertEquals("Autor não encontrado", ex.getMessage());
    Mockito.verify(this.repository, Mockito.times(1)).findById(id);
    Mockito.verifyNoInteractions(this.mapper);
  }

  /* DELETAR */

  @Test
  void quandoCHamarDeletar_ComIdValido_EntaoDeletarComSucesso() {

    Long id = 1L;

    // Arrange
    Autor autor = new Autor();
    autor.setId(id);

    Mockito.when(this.repository.findById(autor.getId())).thenReturn(Optional.of(autor));
    Mockito.doNothing().when(this.repository).deleteById(id);

    // Act
    this.autorService.deletar(id);
    // Assert
    Mockito.verify(this.repository, Mockito.times(1)).findById(id);

  }

  @Test
  void quandoChamarDeletar_ComIdInvalido_EntaoRetornarIllegalArgumentException() {
    Long id = 99L;

    // Arrange
    IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.autorService.deletar(id);
    });

    // Assert
    Assertions.assertEquals("Autor não encontrado", ex.getMessage());
    Mockito.verify(this.repository, Mockito.times(1)).findById(id);
    Mockito.verifyNoInteractions(this.mapper);
  }

}
