package fr.benjaminbillet.dataflow;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.function.Supplier;

@Getter
@Setter
@ConfigurationProperties("api-poller")
@Configuration
public class ApiPollerProperties {
  private String uri;
  private HttpMethod verb = HttpMethod.GET;
}
