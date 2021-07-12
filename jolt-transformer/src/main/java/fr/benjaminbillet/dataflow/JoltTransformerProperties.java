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
@ConfigurationProperties("jolt-transformer")
@Configuration
public class JoltTransformerProperties {
  private JoltSpecResource joltSpecification;
}
