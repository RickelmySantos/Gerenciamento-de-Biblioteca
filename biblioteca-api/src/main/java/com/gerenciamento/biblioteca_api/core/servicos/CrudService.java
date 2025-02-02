package com.gerenciamento.biblioteca_api.core.servicos;

import com.gerenciamento.biblioteca_api.core.modelo.EntidadeBase;
import com.gerenciamento.biblioteca_api.core.repositorios.BaseRepository;
import com.gerenciamento.biblioteca_api.core.util.Bean;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public interface CrudService<E extends EntidadeBase, R extends BaseRepository<E>>
    extends QueryService<E, R> {

  default Optional<E> cadastrar(E entidade) {
    Assert.notNull(entidade, "Entidade não pode ser nula");
    Assert.isNull(entidade.getId(), "O id da entidade não deve estar preenchido");

    this.validarCadastro(entidade);
    this.onCadastrar(entidade);
    Optional<E> result = Optional.of(this.getRepository().save(entidade));
    if (result.isPresent()) {
      this.onCadastroRealizado(result.get());
    }

    return result;
  }

  default Optional<E> atualizar(E entidade) {
    return this.atualizar(entidade, false);
  }

  default Optional<E> atualizar(E entidade, boolean parcial) {
    Assert.notNull(entidade, "Entidade não pode ser nula");
    Assert.notNull(entidade.getId(), "O id da entidade não pode ser nulo");
    Assert.isTrue(entidade.getId() > 0, "O id da entidade deve ser maior que 0");

    Optional<E> resultado = this.buscarPorId(entidade.getId());

    if (resultado.isEmpty()) {
      throw new EntityNotFoundException("Não foi possível encontrar a entidade com o Id informado");
    }

    E entidadeBase = resultado.get();
    entidade.setDataHoraCadastro(entidadeBase.getDataHoraCadastro());
    entidade.setUsuarioCadastro(entidadeBase.getUsuarioCadastro());

    entidade.setDataHoraUltimaAtualizacao(entidadeBase.getDataHoraUltimaAtualizacao());
    entidade.setUsuarioUltimaAtualizacao(entidadeBase.getUsuarioUltimaAtualizacao());

    entidade.setDataHoraExclusao(entidadeBase.getDataHoraExclusao());
    entidade.setUsuarioExclusao(entidadeBase.getUsuarioExclusao());

    this.validarAtualizacao(entidade, entidadeBase, parcial);
    this.onAtualizar(entidade, entidadeBase, parcial);
    Optional<E> result = Optional.of(this.getRepository().save(entidadeBase));

    if (result.isPresent()) {
      this.onAtualizacaoRealizada(result.get(), parcial);
    }

    return result;
  }

  default void excluir(Long id) {
    Assert.notNull(id, "Id não pode ser nulo");
    Assert.isTrue(id > 0, "Id deve ser maior que 0");

    Optional<E> resultado = this.buscarPorId(id);
    if (resultado.isEmpty()) {
      throw new EntityNotFoundException("Não foi possível encontrar a entidade com o Id informado");
    }

    E entidadeBase = resultado.get();
    this.validarExclusao(entidadeBase);
    this.onExcluir(entidadeBase);

    this.getRepository().deleteById(id);

    this.onExclusaoRealizada(entidadeBase);
  }

  default void validarCadastro(E entidade) {

  }

  default void validarAtualizacao(E entidade, E entidadeBase, boolean atualizacaoParcial) {

  }

  default void validarArquivamento(E entidadeBase) {

  }

  default void validarDesarquivamento(E entidadeBase) {

  }

  default void validarExclusao(E entidadeBase) {

  }

  default void onCadastrar(E entidade) {

  }

  default void onCadastroRealizado(E entidade) {

  }

  default void onAtualizar(E entidade, E entidadeBase, boolean atualizacaoParcial) {
    if (atualizacaoParcial) {
      BeanUtils.copyProperties(entidade, entidadeBase, Bean.getNullPropertyNames(entidade));
    } else {
      BeanUtils.copyProperties(entidade, entidadeBase);
    }
  }

  default void onAtualizacaoRealizada(E entidade, boolean atualizacaoParcial) {

  }

  default void onArquivar(E entidadeBase) {

  }

  default void onArquivamentoRealizado(E entidadeBase) {

  }

  default void onDesarquivar(E entidadeBase) {

  }

  default void onDesarquivamentoRealizado(E entidadeBase) {

  }

  default void onExcluir(E entidadeBase) {

  }

  default void onExclusaoRealizada(E entidade) {

  }

}
