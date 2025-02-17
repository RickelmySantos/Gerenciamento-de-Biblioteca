// package com.gerenciamento.biblioteca_api.servicos;

// import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
// import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
// import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
// import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
// import com.gerenciamento.biblioteca_api.modelos.mappers.EmprestimoMapper;
// import com.gerenciamento.biblioteca_api.repositorios.EmprestimoRepository;
// import com.gerenciamento.biblioteca_api.repositorios.LivrosRepository;
// import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
// import java.time.LocalDate;
// import java.util.Optional;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentMatchers;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith(MockitoExtension.class)
// public class EmprestimoServiceTest {

// @InjectMocks
// private EmprestimoService emprestimoService;
// @Mock
// private EmprestimoMapper emprestimoMapper;
// @Mock
// private EmprestimoRepository emprestimoRepository;
// @Mock
// private UsuarioRepository usuarioRepository;
// @Mock
// private LivrosRepository livroRepository;

// @BeforeEach
// void setUp() {
// MockitoAnnotations.openMocks(this);
// }

// /* SALVAR */

// @Test
// void dadaEntidadeValida_QuandoChamarSalvar_EntaoDeveSalvar() {

// // Given
// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);
// emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-10"));
// emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-20"));

// Usuario usuario = new Usuario();
// usuario.setId(1L);

// Livros livros = new Livros();
// livros.setId(1L);

// Emprestimo emprestimo = new Emprestimo();
// emprestimo.setUsuario(usuario);
// emprestimo.setLivros(livros);
// emprestimo.setDataEmprestimo(LocalDate.parse("2025-10-10"));
// emprestimo.setDataDevolucao(LocalDate.parse("2025-10-20"));

// Emprestimo savedEmprestimo = new Emprestimo();
// savedEmprestimo.setId(1L);
// savedEmprestimo.setUsuario(usuario);
// savedEmprestimo.setLivros(livros);
// savedEmprestimo.setDataEmprestimo(LocalDate.parse("2025-10-10"));
// savedEmprestimo.setDataDevolucao(LocalDate.parse("2025-10-20"));

// EmprestimoDto savedEmprestimoDto = new EmprestimoDto();
// savedEmprestimoDto.setId(1L);
// savedEmprestimoDto.setUsuarioId(1L);
// savedEmprestimoDto.setLivroId(1L);
// savedEmprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-10"));
// savedEmprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-20"));

// Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
// Mockito.when(this.livroRepository.findById(1L)).thenReturn(Optional.of(livros));
// Mockito.when(this.emprestimoRepository.save(ArgumentMatchers.any(Emprestimo.class)))
// .thenReturn(savedEmprestimo);
// Mockito.when(this.emprestimoMapper.paraDto(ArgumentMatchers.any(Emprestimo.class)))
// .thenReturn(savedEmprestimoDto);

// // When
// EmprestimoDto result = this.emprestimoService.cadastrar(emprestimoDto);

// // Then
// Assertions.assertEquals(savedEmprestimoDto, result);
// }

// @Test
// void dadoUsuarioNaoEncontrado_QuandoChamarSalvar_EntaoRetornarIllegalArgumentException() {

// // Given
// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);
// emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-10"));
// emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-20"));

// // When & Then
// Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.empty());

// Assertions.assertThrows(IllegalArgumentException.class, () -> {
// this.emprestimoService.cadastrar(emprestimoDto);
// });
// }

// @Test
// void dadoLivroNaoEncontrado_QuandoChamarSalvar_EntaoRetornarIllegalArgumentException() {

// // Given
// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);
// emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-10"));
// emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-20"));

// Usuario usuario = new Usuario();
// usuario.setId(1L);

// // When & Then
// Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
// Mockito.when(this.livroRepository.findById(1L)).thenReturn(Optional.empty());

// Assertions.assertThrows(IllegalArgumentException.class, () -> {
// this.emprestimoService.cadastrar(emprestimoDto);
// });
// }

// /* ATUALIZAR */

// @Test
// void dadaEntidadeValida_QuandoChamarAtualizar_EntaoRetornarEntidadeAtualizada() {

// // Given
// Usuario usuario = new Usuario();
// usuario.setId(1L);

// Livros livros = new Livros();
// livros.setId(1L);

// Emprestimo emprestimo = new Emprestimo();
// emprestimo.setId(1L);
// emprestimo.setUsuario(usuario);
// emprestimo.setLivros(livros);
// emprestimo.setDataEmprestimo(LocalDate.parse("2025-10-10"));
// emprestimo.setDataDevolucao(LocalDate.parse("2025-10-20"));

// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setId(1L);
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);
// emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-11"));
// emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-21"));

// // When

// Mockito.when(this.emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
// Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
// Mockito.when(this.livroRepository.findById(1L)).thenReturn(Optional.of(livros));
// Mockito.when(this.emprestimoRepository.save(ArgumentMatchers.any(Emprestimo.class)))
// .thenReturn(emprestimo);
// Mockito.when(this.emprestimoMapper.paraDto(ArgumentMatchers.any(Emprestimo.class)))
// .thenReturn(emprestimoDto);

// EmprestimoDto result = this.emprestimoService.atualizar(1L, emprestimoDto);

// // Then

// Assertions.assertNotNull(result);
// Assertions.assertEquals(emprestimoDto, result);
// Assertions.assertEquals(emprestimoDto.getUsuarioId(), result.getUsuarioId());
// Assertions.assertEquals(emprestimoDto.getLivroId(), result.getLivroId());

// }

// @Test
// void dadoEmprestimoNaoEncontrado_QuandoChamarAtualizar_EntaoRetornarIllegalArgumentException() {

// // Given

// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setId(1L);
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);

// // When & Then
// IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
// this.emprestimoService.atualizar(null, emprestimoDto);
// });

// Assertions.assertEquals("Emprestimo não encontrado !", ex.getMessage());
// }

// @Test
// void dadoUsuarioNaoEncontrado_QuandoChamarAtualizar_EntaoRetornarIllegalArgumentException() {

// Usuario usuario = new Usuario();
// usuario.setId(1L);

// Livros livros = new Livros();
// livros.setId(1L);

// Emprestimo emprestimo = new Emprestimo();
// emprestimo.setId(1L);
// emprestimo.setUsuario(usuario);
// emprestimo.setLivros(livros);
// emprestimo.setDataEmprestimo(LocalDate.parse("2025-10-11"));
// emprestimo.setDataDevolucao(LocalDate.parse("2025-10-21"));

// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setId(1L);
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);
// emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-11"));
// emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-21"));

// Mockito.when(this.emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
// Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.empty());

// IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
// this.emprestimoService.atualizar(1L, emprestimoDto);
// });

// Assertions.assertEquals("Usuário não encontrado", ex.getMessage());
// }

// @Test
// void dadoLivroNaoEncontrado_QuandoChamarAtualizar_EntaoRetornarIllegalArgumentException() {

// // Given

// Usuario usuario = new Usuario();
// usuario.setId(1L);

// Livros livros = new Livros();
// livros.setId(1L);

// Emprestimo emprestimo = new Emprestimo();
// emprestimo.setId(1L);
// emprestimo.setUsuario(usuario);
// emprestimo.setLivros(livros);
// emprestimo.setDataEmprestimo(LocalDate.parse("2025-10-11"));
// emprestimo.setDataDevolucao(LocalDate.parse("2025-10-21"));

// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setId(1L);
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);
// emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-11"));
// emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-21"));

// Mockito.when(this.emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
// Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
// Mockito.when(this.livroRepository.findById(1L)).thenReturn(Optional.empty());

// IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
// this.emprestimoService.atualizar(1L, emprestimoDto);
// });

// Assertions.assertEquals("Livro não encontrado", ex.getMessage());
// }

// @Test
// void dadaDataInvalida_QuandoChamarAtualizar_EntaoRetornarIllegalException() {

// // Given

// Usuario usuario = new Usuario();
// usuario.setId(1L);

// Livros livros = new Livros();
// livros.setId(1L);

// Emprestimo emprestimo = new Emprestimo();
// emprestimo.setId(1L);
// emprestimo.setUsuario(usuario);
// emprestimo.setLivros(livros);
// emprestimo.setDataEmprestimo(LocalDate.parse("2025-10-11"));
// emprestimo.setDataDevolucao(LocalDate.parse("2025-10-21"));

// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setId(1L);
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);
// emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-21"));
// emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-11"));

// Mockito.when(this.emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
// Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
// Mockito.when(this.livroRepository.findById(1L)).thenReturn(Optional.of(livros));

// IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
// this.emprestimoService.atualizar(1L, emprestimoDto);
// });

// Assertions.assertEquals("Data de devolução não pode ser menor que a data de emprestimo",
// ex.getMessage());
// }

// /* BUSCAR POR ID */

// @Test
// void dadaEntidadeComIdValido_QuandoChamarBuscarPoId_EntaoRetornarEntidade() {

// Long id = 1L;

// // Given

// Usuario usuario = new Usuario();
// usuario.setId(1L);

// Livros livros = new Livros();
// livros.setId(1L);

// Emprestimo emprestimo = new Emprestimo();
// emprestimo.setId(1L);
// emprestimo.setUsuario(usuario);
// emprestimo.setLivros(livros);
// emprestimo.setDataEmprestimo(LocalDate.parse("2025-10-11"));
// emprestimo.setDataDevolucao(LocalDate.parse("2025-10-21"));

// EmprestimoDto emprestimoDto = new EmprestimoDto();
// emprestimoDto.setId(1L);
// emprestimoDto.setUsuarioId(1L);
// emprestimoDto.setLivroId(1L);
// emprestimoDto.setDataEmprestimo(LocalDate.parse("2025-10-11"));
// emprestimoDto.setDataDevolucao(LocalDate.parse("2025-10-21"));

// // When

// Mockito.when(this.emprestimoRepository.findById(emprestimo.getId()))
// .thenReturn(Optional.of(emprestimo));
// Mockito.when(this.emprestimoMapper.paraDto(emprestimo)).thenReturn(emprestimoDto);

// EmprestimoDto result = this.emprestimoService.buscarPorId(id);

// // Then

// Assertions.assertNotNull(result);
// Assertions.assertEquals(emprestimoDto, result);
// Assertions.assertEquals(emprestimoDto.getUsuarioId(), result.getUsuarioId());
// Assertions.assertEquals(emprestimoDto.getLivroId(), result.getLivroId());
// }

// @Test
// void dadaEntidadeComIdInvalido_QuandoChamarBuscarPorId_EntaoRetornarIllegalArgumentException() {

// // Given

// Long id = 1L;

// // When & Then

// Mockito.when(this.emprestimoRepository.findById(id)).thenReturn(Optional.empty());

// IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
// this.emprestimoService.buscarPorId(id);
// });

// Assertions.assertEquals("Emprestimo não encontrado !", ex.getMessage());
// }

// /* DELETAR */

// @Test
// void dadaEntidadeComIdValido_QuandoChamarDeletar_EntaoDeletarEntidade() {

// Long id = 1L;

// // Given
// Mockito.when(this.emprestimoRepository.existsById(id)).thenReturn(true);

// // When

// this.emprestimoService.deletar(id);

// // Then

// Mockito.verify(this.emprestimoRepository, Mockito.times(1)).deleteById(id);

// }

// @Test
// void dadaEntidadeComIdInvalido_QuandoChamarDeletar_EntaoRetornarIllegalArgumentException() {

// // Given

// Long id = 1L;

// // When & Then

// Mockito.when(this.emprestimoRepository.existsById(id)).thenReturn(false);

// IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
// this.emprestimoService.deletar(id);
// });

// Assertions.assertEquals("Emprestimo não encontrado !", ex.getMessage());
// }
// }
