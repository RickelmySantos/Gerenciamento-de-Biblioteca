package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.modelos.dtos.AutorDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.mappers.AutorMapper;
import com.gerenciamento.biblioteca_api.repositorios.AutorRepository;
import com.gerenciamento.biblioteca_api.repositorios.LivrosRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AutorService {

  private LivrosRepository livrosRepository;

  private AutorRepository repository;

  private AutorMapper mapper;

  public AutorService(AutorRepository repository, AutorMapper mapper, LivrosRepository livrosRepository) {
    this.livrosRepository = livrosRepository;
    this.repository = repository;
    this.mapper = mapper;
  }

  public List<AutorDto> listAll() {
    List<Autor> autores = this.repository.findAll();

    return autores.stream().map(this.mapper::paraDto).toList();
  }

  public AutorDto buscarPorId(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");

    Autor autor = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

    return this.mapper.paraDto(autor);
  }

  public AutorDto cadastrar(AutorDto autorDto) {
    Assert.notNull(autorDto, "Autor não pode ser nulo");
    Assert.isNull(autorDto.getId(), "Id deve ser nulo");

    Autor autor = this.mapper.paraEntidade(autorDto);

    List<Livros> livrosGerenciados = new ArrayList<>();
    if (autor.getLivros() != null) {
      for (Livros livro : autor.getLivros()) {
        if (livro.getId() != null) {
          Livros livroExistente = this.livrosRepository.findById(livro.getId())
              .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
          livrosGerenciados.add(livroExistente);
        } else {
          livro.setAutor(autor);
          livrosGerenciados.add(livro);
        }
      }
    }
    autor.setLivros(livrosGerenciados);

    autor = this.repository.save(autor);

    return this.mapper.paraDto(autor);
  }

  public AutorDto atualizar(AutorDto autorDto) {

    Assert.notNull(autorDto, "Autor não pode ser nulo");
    Assert.notNull(autorDto.getId(), "Id não pode ser nulo");

    Autor autor = this.repository.findById(autorDto.getId())
        .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

    Autor autorAtualizado = this.mapper.paraEntidade(autorDto);
    autorAtualizado.setId(autor.getId());

    return this.mapper.paraDto(this.repository.save(autorAtualizado));

  }

  public void deletar(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");
    Assert.isTrue(id > 0, "Id deve ser maior que 0");

    Optional<Autor> result = this.repository.findById(id);
    if (result.isEmpty()) {
      throw new IllegalArgumentException("Autor não encontrado");
    }

    this.repository.deleteById(id);
  }
}
