package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.modelos.dtos.LivrosDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.mappers.LivrosMapper;
import com.gerenciamento.biblioteca_api.repositorios.AutorRepository;
import com.gerenciamento.biblioteca_api.repositorios.LivrosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class LivrosService {

  private LivrosRepository repository;
  private LivrosMapper mapper;
  private AutorRepository autorRepository;

  public LivrosService(LivrosRepository repository, LivrosMapper mapper,
      AutorRepository autorRepository) {
    this.repository = repository;
    this.mapper = mapper;
    this.autorRepository = autorRepository;
  }

  public LivrosDto cadastrar(LivrosDto livroDto) {
    Assert.notNull(livroDto, "Livro não pode ser nulo");
    Assert.isNull(livroDto.getId(), "Id deve ser nulo");
    Assert.notNull(livroDto.getAutorDto().getId(), "AutorId não pode ser nulo");

    Autor autor = this.autorRepository.findById(livroDto.getAutorDto().getId())
        .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

    Livros livro = this.mapper.paraEntidade(livroDto);

    livro.setAutor(autor);

    Livros livroSalvo = this.repository.save(livro);

    return this.mapper.paraDto(livroSalvo);

  }

  public LivrosDto atualizar(LivrosDto livroDto) {

    Assert.notNull(livroDto, "Livro não pode ser nulo");
    Assert.notNull(livroDto.getId(), "Id não pode ser nulo");

    Livros livro = this.repository.findById(livroDto.getId())
        .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

    Livros livroAtualizado = this.mapper.paraEntidade(livroDto);
    livroAtualizado.setId(livro.getId());

    return this.mapper.paraDto(this.repository.save(livroAtualizado));
  }

  public LivrosDto buscarPorId(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");
    Livros livros = this.repository.findByWithAutor(id)
        .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

    return this.mapper.paraDto(livros);
  }

  public List<LivrosDto> listAll() {

    List<Livros> livros = this.repository.findAll();

    return livros.stream().map(this.mapper::paraDto).toList();
  }

  public void deletar(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");
    Assert.isTrue(id > 0, "Id deve ser maior que 0");

    Optional<Livros> result = this.repository.findById(id);
    if (result.isEmpty()) {
      throw new IllegalArgumentException("não foi possível encontrar o livro com o id informado");
    }

    this.repository.deleteById(id);
  }

}
