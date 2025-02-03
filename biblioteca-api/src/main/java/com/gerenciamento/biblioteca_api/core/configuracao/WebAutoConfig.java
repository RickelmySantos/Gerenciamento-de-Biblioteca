package com.gerenciamento.biblioteca_api.core.configuracao;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Slf4j
public class WebAutoConfig implements WebMvcConfigurer {

  @PostConstruct
  public void init() {
    WebAutoConfig.log.info("LOADED >>>>> WebAutoConfig ");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
  }

}
