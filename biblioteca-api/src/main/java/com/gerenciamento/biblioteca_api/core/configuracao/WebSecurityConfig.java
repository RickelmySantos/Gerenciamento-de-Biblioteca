package com.gerenciamento.biblioteca_api.core.configuracao;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@RequiredArgsConstructor
@AutoConfiguration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {

  @PostConstruct
  public void init() {
    WebSecurityConfig.log.info("LOADED >>>>> WebSecurityConfig");
  }

  public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc)
      throws Exception {

    WebSecurityConfig.log.info("LOADED >>>>> SecurityFilterChain");

    http.csrf(csrf -> csrf.disable());
    http.cors(cors -> Customizer.withDefaults());
    http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

    http.authorizeHttpRequests(authorizeRequests -> {
      authorizeRequests.requestMatchers(mvc.pattern("/v3/api-docs")).permitAll()
          .requestMatchers(mvc.pattern("/swagger-resources/**")).permitAll()
          .requestMatchers(mvc.pattern("/swagger-ui/**")).permitAll()
          .requestMatchers(mvc.pattern("/swagger-ui/index.html**")).permitAll()
          .requestMatchers(mvc.pattern("/actuator/health")).permitAll()
          // APP
          .anyRequest().authenticated();
    });
    http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> Customizer.withDefaults()));
    http.sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
