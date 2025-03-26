package com.gerenciamento.biblioteca_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

@ComponentScan(basePackages = "com.gerenciamento.biblioteca_api")
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
public class BibliotecaApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(BibliotecaApiApplication.class, args);
  }

}
