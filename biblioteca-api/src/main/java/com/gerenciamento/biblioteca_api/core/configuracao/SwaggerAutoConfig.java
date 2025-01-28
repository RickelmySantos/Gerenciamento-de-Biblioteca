package com.gerenciamento.biblioteca_api.core.configuracao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
// @ConditionalOnProperty("openapi.title")
@Slf4j
public class SwaggerAutoConfig {

  @Value("${openapi.title}")
  private String apiTitle;
  @Value("${openapi.description}")
  private String apiDescription;
  @Value("${openapi.version}")
  private String apiVersion;
  @Value("${openapi.contact.name}")
  private String apiContactName;
  @Value("${openapi.contact.email}")
  private String apiContactUrl;
  @Value("${openapi.contact.url}")
  private String serverUrl;
  @Value("${openapi.license.name}")
  private String contextPath;

  @PostConstruct
  public void init() {
    SwaggerAutoConfig.log.info("LOADED >>>> SwaggerAutoConfig");
  }

  @Bean
  public OpenAPI myOpenAPI() {
    return new OpenAPI().info(this.getInfo()).servers(List.of(this.getServer()));
  }

  private Server getServer() {
    Server server = new Server();
    server.setUrl(this.serverUrl + this.contextPath);
    return server;
  }

  private Info getInfo() {
    Contact contact = new Contact();
    contact.setName(this.apiContactName);
    contact.setEmail(this.apiContactUrl);
    contact.setUrl(this.apiContactUrl);

    return new Info().title(this.apiTitle).version(this.apiVersion).contact(contact)
        .description(this.apiDescription);
  }
}
