package fr.benjaminbillet.dataflow;

import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class InMemoryStore {
  private final InMemoryStoreProperties properties;
  private final Map<String, String> store = new ConcurrentHashMap<>();

  @Bean
  public Consumer<String> process() {
    return data -> {
      String key = JsonPath.parse(data).read(properties.getKeyJsonPath());
      store.put(key, data);
      log.info("Stored data: {}, {}", key, data);
    };
  }

  public Optional<String> lookup(String key) {
    return Optional.ofNullable(store.get(key));
  }
}
