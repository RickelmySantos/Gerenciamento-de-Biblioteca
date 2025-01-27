package com.gerenciamento.biblioteca_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.gerenciamento.biblioteca_api")
@SpringBootApplication
public class BibliotecaApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BibliotecaApiApplication.class, args);
  }

}
