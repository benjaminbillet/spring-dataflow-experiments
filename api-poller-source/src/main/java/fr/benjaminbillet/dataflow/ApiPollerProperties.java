package fr.benjaminbillet.dataflow;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ConfigurationProperties("api-poller")
@Configuration
public class ApiPollerProperties {

  @NotBlank
  private String uri;

  @NotNull
  private HttpMethod verb = HttpMethod.GET;
}
