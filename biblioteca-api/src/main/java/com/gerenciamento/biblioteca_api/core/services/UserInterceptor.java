package com.gerenciamento.biblioteca_api.core.services;

import com.gerenciamento.biblioteca_api.modelos.entidades.Usuario;
import com.gerenciamento.biblioteca_api.modelos.enums.TipoUsuario;
import com.gerenciamento.biblioteca_api.repositorios.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class UserInterceptor implements HandlerInterceptor {

  private final UsuarioRepository usuarioRepository;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getPrincipal() instanceof Jwt) {

      Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String userId = jwt.getClaim("sub");
      String userName = jwt.getClaim("preferred_username");
      String email = jwt.getClaim("email");

      this.usuarioRepository.findById(userId).orElseGet(() -> {
        Usuario usuario = Usuario.builder().id(userId).nome(userName).email(email)
            .tipoUsuario(TipoUsuario.USUARIO).build();
        return this.usuarioRepository.save(usuario);
      });
    }
    return true;
  }

}
