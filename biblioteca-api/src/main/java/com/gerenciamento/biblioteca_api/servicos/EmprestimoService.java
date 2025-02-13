package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.core.services.UserAutenticadoService;
import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoRequestDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import com.gerenciamento.biblioteca_api.modelos.mappers.EmprestimoMapper;
import com.gerenciamento.biblioteca_api.repositorios.EmprestimoRepository;
import com.gerenciamento.biblioteca_api.repositorios.LivrosRepository;
import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

  private EmprestimoRepository repository;
  private EmprestimoMapper mapper;
  private UsuarioRepository usuarioRepository;
  private LivrosRepository livroRepository;
  private UserAutenticadoService userAutenticadoService;

  public EmprestimoService(EmprestimoRepository repository, EmprestimoMapper mapper,
      UsuarioRepository usuarioRepository, LivrosRepository livroRepository,
      UserAutenticadoService userAutenticadoService) {
    this.repository = repository;
    this.mapper = mapper;
    this.usuarioRepository = usuarioRepository;
    this.livroRepository = livroRepository;
    this.userAutenticadoService = userAutenticadoService;
  }


  @Transactional
  public EmprestimoDto criarEmprestimo(EmprestimoRequestDto requestDto) {

    String userId = this.userAutenticadoService.getUserId();

    Usuario usuario = this.usuarioRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

    Livros livro = this.livroRepository.findById(requestDto.getLivroId())
        .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

    boolean emprestimoAtivo =
        this.repository.existsByLivrosAndDataDevolucaoAfter(livro, LocalDate.now());

    if (emprestimoAtivo) {
      throw new IllegalArgumentException("Livro indisponível para empréstimo no momento.");
    }

    Emprestimo emprestimo = Emprestimo.builder().usuario(usuario).livros(livro)
        .dataEmprestimo(LocalDate.now()).dataDevolucao(LocalDate.now().plusDays(7)).build();

    return this.mapper.paraDto(this.repository.save(emprestimo));
  }

  @Transactional
  public EmprestimoDto atualizar(Long id, EmprestimoDto emprestimoDto) {
    Emprestimo emprestimo = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Emprestimo não encontrado !"));

    Livros livros = this.livroRepository.findById(emprestimoDto.getLivrosDto().getId())
        .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

    if (emprestimoDto.getDataDevolucao().isBefore(emprestimoDto.getDataEmprestimo())) {
      throw new IllegalArgumentException(
          "Data de devolução não pode ser menor que a data de emprestimo");
    }

    emprestimo.setLivros(livros);
    emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
    emprestimo.setDataDevolucao(emprestimoDto.getDataDevolucao());

    return this.mapper.paraDto(this.repository.save(emprestimo));
  }



  public EmprestimoDto buscarPorId(Long id) {
    Emprestimo emprestimo = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Emprestimo não encontrado !"));

    return this.mapper.paraDto(emprestimo);
  }

  public List<EmprestimoDto> listAll() {
    return this.repository.findAll().stream().map(this.mapper::paraDto).toList();
  }

  public void deletar(Long id) {
    if (this.repository.existsById(id)) {
      this.repository.deleteById(id);
    } else {
      throw new IllegalArgumentException("Emprestimo não encontrado !");
    }
  }

}
