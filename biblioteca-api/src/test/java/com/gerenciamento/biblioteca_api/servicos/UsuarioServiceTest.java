package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.mock.factory.EmprestimoFactory;
import com.gerenciamento.biblioteca_api.mock.factory.LivrosFactory;
import com.gerenciamento.biblioteca_api.mock.factory.UsuarioFactory;
import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
import com.gerenciamento.biblioteca_api.modelos.dtos.UsuarioDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import com.gerenciamento.biblioteca_api.modelos.mappers.UsuarioMapper;
import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

  @InjectMocks
  private UsuarioService usuarioService;
  @Mock
  private UsuarioRepository usuarioRepository;
  @Mock
  private UsuarioMapper usuarioMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void dadaEntidadeValida_QuandoChamarMetodoSalvar_EntaoDeveRetornarEntidadeSalva() {
    Long id = 1L;
    // Given
    Usuario usuario = UsuarioFactory.instance.create();
    usuario.setId(id);

    UsuarioDto usuarioDto = new UsuarioDto();

    usuarioDto.setNome(usuario.getNome());
    usuarioDto.setEmail(usuario.getEmail());
    usuarioDto.setSenha(usuario.getSenha());
    usuarioDto.setTipoUsuario(usuario.getTipoUsuario());
    usuarioDto.setEmprestimoDto(usuario.getEmprestimo().stream()
        .map(emprestimo -> new EmprestimoDto()).collect(Collectors.toList()));

    // When
    Mockito.when(this.usuarioRepository.save(ArgumentMatchers.any(Usuario.class)))
        .thenReturn(usuario);
    Mockito.when(this.usuarioMapper.paraEntidade(ArgumentMatchers.any(UsuarioDto.class)))
        .thenReturn(usuario);
    Mockito.when(this.usuarioMapper.paraDto(ArgumentMatchers.any(Usuario.class)))
        .thenReturn(usuarioDto);

    UsuarioDto usuarioSalvo = this.usuarioService.cadastrar(usuarioDto);

    // Then
    Assertions.assertNotNull(usuarioSalvo);
    Assertions.assertEquals(usuarioDto, usuarioSalvo);
    Assertions.assertEquals(usuarioDto.getNome(), usuarioSalvo.getNome());
  }

  @Test
  void dadaEntidadeInvalida_QuandoChamarMetodoSalvar_EntaoRetornarIllegalArgumentException() {
    // Given
    UsuarioDto usuarioDto = null;

    // When
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> this.usuarioService.cadastrar(usuarioDto));

    // Then
    Assertions.assertEquals("Usuario não pode ser nulo", exception.getMessage());
  }

  /* Atualizar */

  @Test
  void dadaEntidadeValida_QuandoChamarMetodoAtualizar_EntaoDeveRetornarEntidadeAtualizada() {
    Long id = 1L;
    // Given
    Usuario usuario = UsuarioFactory.instance.create();
    usuario.setId(id);

    UsuarioDto usuarioDto = new UsuarioDto();

    usuarioDto.setId(usuario.getId());
    usuarioDto.setNome(usuario.getNome());
    usuarioDto.setEmail(usuario.getEmail());
    usuarioDto.setSenha(usuario.getSenha());
    usuarioDto.setTipoUsuario(usuario.getTipoUsuario());
    usuarioDto.setEmprestimoDto(usuario.getEmprestimo().stream()
        .map(emprestimo -> new EmprestimoDto()).collect(Collectors.toList()));

    // When
    Mockito.when(this.usuarioRepository.findById(ArgumentMatchers.anyLong()))
        .thenReturn(java.util.Optional.of(usuario));
    Mockito.when(this.usuarioRepository.save(ArgumentMatchers.any(Usuario.class)))
        .thenReturn(usuario);
    Mockito.when(this.usuarioMapper.paraEntidade(ArgumentMatchers.any(UsuarioDto.class)))
        .thenReturn(usuario);
    Mockito.when(this.usuarioMapper.paraDto(ArgumentMatchers.any(Usuario.class)))
        .thenReturn(usuarioDto);

    UsuarioDto usuarioAtualizado = this.usuarioService.atualizar(usuarioDto);

    // Then
    Assertions.assertNotNull(usuarioAtualizado);
    Assertions.assertEquals(usuarioDto, usuarioAtualizado);
    Assertions.assertEquals(usuarioDto.getNome(), usuarioAtualizado.getNome());
  }

  @Test
  void dadaEntidadeInvalida_QuandoChamarMetodoAtualizar_EntaoRetornarIllegalArgumentException() {
    Long id = 1L;

    // Given
    Usuario usuario = UsuarioFactory.instance.create();
    usuario.setId(id);

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId(usuario.getId());
    usuarioDto.setNome(usuario.getNome());
    usuarioDto.setEmail(usuario.getEmail());
    usuarioDto.setSenha(usuario.getSenha());
    usuarioDto.setTipoUsuario(usuario.getTipoUsuario());
    usuarioDto.setEmprestimoDto(usuario.getEmprestimo().stream()
        .map(emprestimo -> new EmprestimoDto()).collect(Collectors.toList()));

    // When
    Mockito.when(this.usuarioRepository.findById(id)).thenReturn(Optional.empty());
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> this.usuarioService.atualizar(usuarioDto));
    // Then
    Assertions.assertEquals("Usuario não encontrado", exception.getMessage());
  }

  /* Buscar por id */

  @Test
  void dadaEntidadeComIdValido_QuandoChamarMetodoBuscarPorId_EntaoRetornarEntidade() {
    Long id = 1L;

    // Given
    Usuario usuario = UsuarioFactory.instance.create();
    usuario.setId(id);

    Livros livro = LivrosFactory.instance.create();
    livro.setId(50L);

    Emprestimo emprestimo = EmprestimoFactory.instance.create();
    emprestimo.setId(1L);
    emprestimo.setUsuario(usuario);
    emprestimo.setLivros(livro);

    usuario.setEmprestimo(List.of(emprestimo));

    UsuarioDto usuarioDto = new UsuarioDto();
    usuarioDto.setId(usuario.getId());
    usuarioDto.setNome(usuario.getNome());
    usuarioDto.setEmail(usuario.getEmail());
    usuarioDto.setSenha(usuario.getSenha());
    usuarioDto.setTipoUsuario(usuario.getTipoUsuario());
    usuarioDto.setEmprestimoDto(usuario
        .getEmprestimo().stream().map(e -> new EmprestimoDto(e.getId(), e.getDataEmprestimo(),
            e.getDataDevolucao(), e.getUsuario().getId(), e.getLivros().getId()))
        .collect(Collectors.toList()));

    // When
    Mockito.when(this.usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
    Mockito.when(this.usuarioMapper.paraDto(usuario)).thenReturn(usuarioDto);

    UsuarioDto result = this.usuarioService.buscarPorId(usuario.getId());

    // Then
    Assertions.assertNotNull(result);
    Assertions.assertEquals(usuarioDto, result);
    Assertions.assertEquals(usuarioDto.getNome(), result.getNome());
    Assertions.assertEquals(usuarioDto.getTipoUsuario(), result.getTipoUsuario());
  }

  @Test
  void dadaEntidadeComIdInvalida_QuandoChamarMetodoBuscarPorId_EntaoRetornarIllegalArgumentException() {
    Long id = 1L;

    // Given
    Usuario usuario = UsuarioFactory.instance.create();
    usuario.setId(id);

    // When
    Mockito.when(this.usuarioRepository.findById(id)).thenReturn(Optional.empty());
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> this.usuarioService.buscarPorId(id));

    // Then
    Assertions.assertEquals("Usuario não encontrado", exception.getMessage());
  }

  /* Deletar */

  @Test
  void dadaEntidadeComIdValido_QuandoChamarMetodoDeletar_EntaoDeletarEntidade() {

    Long id = 1L;

    // Given
    Usuario usuario = UsuarioFactory.instance.create();
    usuario.setId(id);

    Mockito.when(this.usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

    // When
    this.usuarioService.deletar(id);

    // Then
    Mockito.verify(this.usuarioRepository, Mockito.times(1)).delete(usuario);
  }

  @Test
  void dadaEntidadeComIdInvalido_QuandoChamarMetodoDeletar_EntaoRetornarIllegalArgumentException() {
    Long id = 1L;

    // Given
    Usuario usuario = UsuarioFactory.instance.create();
    usuario.setId(id);

    // When
    Mockito.when(this.usuarioRepository.findById(id)).thenReturn(Optional.empty());
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> this.usuarioService.deletar(id));

    // Then
    Assertions.assertEquals("Usuario não encontrado", exception.getMessage());
  }
}
