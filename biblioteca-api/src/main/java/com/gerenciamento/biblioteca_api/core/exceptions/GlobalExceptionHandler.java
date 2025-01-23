package com.gerenciamento.biblioteca_api.core.exceptions;

import jakarta.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex,
      WebRequest request) {
    var status = HttpStatus.FORBIDDEN;
    GlobalExceptionHandler.log.error(status.getReasonPhrase(), ex);

    var erro = new ErroDTO(status, "Usuário sem permissão");
    return new ResponseEntity<>(erro, status);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
    var status = HttpStatus.INTERNAL_SERVER_ERROR;
    GlobalExceptionHandler.log.error(status.getReasonPhrase(), ex);

    var erro = new ErroDTO(status);
    erro.setMessage("Erro interno no servidor. Entre em contato com um administrador do sistema.");
    return new ResponseEntity<>(erro, status);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
    var status = HttpStatus.NOT_FOUND;
    GlobalExceptionHandler.log.error(status.getReasonPhrase(), ex);

    var erro = new ErroDTO(status);
    erro.setMessage("Entidade não encontrada.");
    return new ResponseEntity<>(erro, status);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    HttpStatus status = (HttpStatus) statusCode;
    GlobalExceptionHandler.log.error(status.getReasonPhrase(), ex);

    var erro = new ErroDTO(status);
    erro.setMessage("Requisição inválida. Verifique o corpo desta requisição.");

    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(x -> x.getObjectName() + "." + x.getField() + " " + x.getDefaultMessage()).toList();

    erro.addErrors(errors);

    return new ResponseEntity<>(erro, headers, status);

  }

}