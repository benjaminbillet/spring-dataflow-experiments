package fr.benjaminbillet.dataflow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Getter
@Setter
@Slf4j
public class JoltSpecResource {
  private String data;

  public JoltSpecResource(String data) {
    data = data.trim();

    if (data.startsWith("{")) {
      // this is an inline JOLT spec, take it directly
      this.data = data;
      return;
    }

    try {
      this.data = loadUrlResource(new URL(data));
    } catch (MalformedURLException e) {
      log.error("Spec resource URL is malformed or not supported", e);
      throw new IllegalStateException(e);
    } catch (IOException e) {
      log.error("Cannot load spec resource | data={}", data, e);
      throw new IllegalStateException(e);
    }
  }

  public static String loadUrlResource(URL url) throws IOException {
    return IOUtils.toString(url.openStream(), StandardCharsets.UTF_8);
  }
}