package com.gerenciamento.biblioteca_api.core.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private final int status;
  private final String error;
  private List<String> errors;

  @Setter
  private String message;

  public ErroDTO(HttpStatus httpStatus) {
    this.status = httpStatus.value();
    this.error = httpStatus.getReasonPhrase();
  }

  public ErroDTO(HttpStatus httpStatus, String message) {
    this(httpStatus);
    this.message = message;
  }

  public void addErrors(List<String> errors) {
    if (errors != null) {
      errors = new ArrayList<>();
    }
    errors.addAll(errors);
  }

}
