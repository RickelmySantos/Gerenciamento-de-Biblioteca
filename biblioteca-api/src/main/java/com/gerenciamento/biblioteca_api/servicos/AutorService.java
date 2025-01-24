package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.modelos.dtos.AutorDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;
import com.gerenciamento.biblioteca_api.modelos.entidades.Livros;
import com.gerenciamento.biblioteca_api.modelos.mappers.AutorMapper;
import com.gerenciamento.biblioteca_api.repositorios.AutorRepository;
import com.gerenciamento.biblioteca_api.repositorios.LivrosRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AutorService {

  private LivrosRepository livrosRepository;

  private AutorRepository repository;

  private AutorMapper mapper;

  public AutorService(AutorRepository repository, AutorMapper mapper,
      LivrosRepository livrosRepository) {
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
    this.validarAutorDto(autorDto);
    this.validarFormatoData(autorDto.getDataNascimento());

    Autor autor = this.mapper.paraEntidade(autorDto);

    autor.setLivros(this.processarLivros(autor));

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


  private void validarAutorDto(AutorDto autorDto) {
    Assert.notNull(autorDto, "Autor não pode ser nulo");
    Assert.isNull(autorDto.getId(), "Id deve ser nulo");

    if (autorDto.getDataNascimento() == null) {
      throw new IllegalArgumentException("Data de nascimento não pode ser nula");
    }
  }

  private void validarFormatoData(LocalDate data) {

    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String dataFormatada = data.format(formatter);
      LocalDate.parse(dataFormatada, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Formato de data inválido. Use o formato dd/MM/yyyy");
    }
  }

  private List<Livros> processarLivros(Autor autor) {
    if (autor.getLivros() == null) {
      return Collections.emptyList();
    }

    return autor.getLivros().stream().map(livro -> {
      if (livro.getId() != null) {
        return this.livrosRepository.findById(livro.getId()).orElseThrow(
            () -> new EntityNotFoundException("Livro com ID " + livro.getId() + " não encontrado"));
      } else {
        livro.setAutor(autor);
        return livro;
      }
    }).toList();
  }
}
