package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.modelos.dtos.EmprestimoDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Emprestimo;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import com.gerenciamento.biblioteca_api.modelos.mappers.EmprestimoMapper;
import com.gerenciamento.biblioteca_api.repositorios.EmprestimoRepository;
import com.gerenciamento.biblioteca_api.repositorios.LivrosRepository;
import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

  private final EmprestimoRepository repository;
  private final EmprestimoMapper mapper;
  private final UsuarioRepository usuarioRepository;
  private final LivrosRepository livroRepository;

  public EmprestimoService(EmprestimoRepository repository, EmprestimoMapper mapper,
      UsuarioRepository usuarioRepository, LivrosRepository livroRepository) {
    this.repository = repository;
    this.mapper = mapper;
    this.usuarioRepository = usuarioRepository;
    this.livroRepository = livroRepository;
  }


  public EmprestimoDto salvar(EmprestimoDto emprestimoDto) {

    Usuario usuario = this.usuarioRepository.findById(emprestimoDto.getUsuarioId())
        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    Livros livros = this.livroRepository.findById(emprestimoDto.getLivroId())
        .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

    Emprestimo emprestimo = new Emprestimo();
    emprestimo.setUsuario(usuario);
    emprestimo.setLivros(livros);
    emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
    emprestimo.setDataDevolucao(emprestimoDto.getDataDevolucao());

    Emprestimo save = this.repository.save(emprestimo);

    return this.mapper.paraDto(save);

  }

  public EmprestimoDto atualizar(Long id, EmprestimoDto emprestimoDto) {
    Emprestimo emprestimo = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Emprestimo não encontrado !"));

    Usuario usuario = this.usuarioRepository.findById(emprestimoDto.getUsuarioId())
        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

    Livros livros = this.livroRepository.findById(emprestimoDto.getLivroId())
        .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

    if (emprestimoDto.getDataDevolucao().isBefore(emprestimoDto.getDataEmprestimo())) {
      throw new IllegalArgumentException(
          "Data de devolução não pode ser menor que a data de emprestimo");
    }

    emprestimo.setUsuario(usuario);
    emprestimo.setLivros(livros);
    emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
    emprestimo.setDataDevolucao(emprestimoDto.getDataDevolucao());

    Emprestimo save = this.repository.save(emprestimo);

    return this.mapper.paraDto(save);
  }


  public EmprestimoDto buscarPorId(Long id) {
    Emprestimo emprestimo = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Emprestimo não encontrado !"));

    return this.mapper.paraDto(emprestimo);
  }

  public List<EmprestimoDto> listar() {
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
