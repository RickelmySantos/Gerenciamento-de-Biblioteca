package com.gerenciamento.biblioteca_api.core.configuracao;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@RequiredArgsConstructor
@AutoConfiguration
@EnableWebSecurity
// @EnableMethodSecurity(securedEnabled = true)
@Slf4j
public class WebSecurityConfig {
  // @Value("${security.oauth2.resourceserver.jwt.jwk-set-uri}")
  // private String jwkSetUri;

  @Value("${security.oauth2.resourceserver.jwt.issuer-uri}")
  private String jwkSetUri;

  @Value("${security.enabled:false}")
  protected Boolean securityEnabled;


  @PostConstruct
  public void init() {
    WebSecurityConfig.log.info("LOADED >>>>> WebSecurityConfig");
    WebSecurityConfig.log.info("Security Enabled: " + this.securityEnabled);
  }

  @Bean
  MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc)
      throws Exception {

    WebSecurityConfig.log.debug("LOADED >>>>> SecurityFilterChain");

    http.csrf(csrf -> csrf.disable());

    if (this.isSecurityDisabled()) {
      http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
      http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll());
    } else {
      http.cors(cors -> Customizer.withDefaults());
      http.authorizeHttpRequests(authorizeRequests -> {
        authorizeRequests.requestMatchers(mvc.pattern("/v3/api-docs")).permitAll()
            .requestMatchers("/api/usuarios/me").hasRole("ADMIN")
            .requestMatchers(mvc.pattern("/swagger-resources/**")).permitAll()
            .requestMatchers(mvc.pattern("/swagger-ui/**")).permitAll()
            .requestMatchers(mvc.pattern("/swagger-ui/index.html**")).permitAll()
            .requestMatchers(mvc.pattern("/actuator/health")).permitAll()
            // APP
            .anyRequest().authenticated();
      });

      // http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> Customizer.withDefaults()));
      http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(this.jwtDecoder())
          .jwtAuthenticationConverter(this.jwtAuthenticationConverter())));
      http.sessionManagement(
          session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }
    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

    converter.setJwtGrantedAuthoritiesConverter(jwt -> {
      Collection<GrantedAuthority> authorities = new ArrayList<>();

      if (jwt.getClaim("resource_access") != null) {

        Map<String, Object> resourcesAccess = (Map<String, Object>) jwt.getClaim("resource_access");

        if (resourcesAccess.containsKey("biblioteca-ui")) {
          List<String> roles =
              (List<String>) ((Map) resourcesAccess.get("biblioteca-ui")).get("roles");

          roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        }
      }

      return authorities;
    });

    return converter;
  }

  private Boolean isSecurityDisabled() {
    return this.securityEnabled != null && !this.securityEnabled;
  }
}
