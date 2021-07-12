package fr.benjaminbillet.dataflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JoltTransformerApplication {

  public static void main(String[] args) {
    SpringApplication.run(fr.benjaminbillet.dataflow.JoltTransformer.class, args);
    // JoltTransformerProperties props = new JoltTransformerProperties();
    // props.setJoltSpecification(new JoltSpecResource("file:./jolt-transformer/src/main/resources/testspec.json"));
    // new JoltTransformer(new ObjectMapper(), props).joltTransform().apply("{ \"key\": 1 }");
  }

}
