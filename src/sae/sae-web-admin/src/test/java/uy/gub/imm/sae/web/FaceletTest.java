package uy.gub.imm.sae.web;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FaceletTest {
    @Test
    void givenXhtmlPages_whenRead_DontContainsEscapeEntry() throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get("src/main/webapp"), Integer.MAX_VALUE)) {
            List<String> textos = stream
                    .filter(Files::isRegularFile)
                    .filter(path -> StringUtils.endsWith(path.getFileName().toString(), ".xhtml"))
                    .map(path -> {
                        try {
                            return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                        } catch (IOException e) {
                            return "";
                        }
                    })
                    .filter(text -> StringUtils.contains(text, "escape=\"false\""))
                    .collect(Collectors.toList());

            assertThat(textos).isEmpty();
        }
    }

}
