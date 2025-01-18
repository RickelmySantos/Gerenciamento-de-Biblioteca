package com.gerenciamento.biblioteca_api.servicos;

import com.gerenciamento.biblioteca_api.modelos.dtos.AutorDto;
import com.gerenciamento.biblioteca_api.modelos.entidades.Autor;
import com.gerenciamento.biblioteca_api.modelos.mappers.AutorMapper;
import com.gerenciamento.biblioteca_api.repositorios.AutorRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AutorService {

  protected AutorRepository repository;

  protected AutorMapper mapper;


  public List<AutorDto> listar() {
    List<Autor> autores = this.repository.findAll();

    return autores.stream().map(this.mapper::paraDto).toList();
  }

  public AutorDto buscarPorId(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");

    Autor autor = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

    return this.mapper.paraDto(autor);
  }

  public AutorDto salvar(AutorDto autorDto) {
    Assert.notNull(autorDto, "Autor não pode ser nulo");
    Assert.isNull(autorDto.getId(), "Id deve ser nulo");

    Autor autor = this.repository.save(this.mapper.paraEntidade(autorDto));

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



}
