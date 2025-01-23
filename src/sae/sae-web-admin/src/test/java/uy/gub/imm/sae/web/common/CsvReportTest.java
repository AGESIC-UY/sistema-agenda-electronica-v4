package uy.gub.imm.sae.web.common;

import static org.assertj.core.api.Assertions.assertThat;
import static uy.gub.imm.sae.web.common.CsvReport.HEADERS;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class CsvReportTest {

  @Test
  void givenCsvRows_whenConvertToCSV_thenConvertToByteArray() {
    List<CsvRow> rows = Arrays.asList(new CsvRow(1, "Actualizado correctamente"), new CsvRow(2, "No pudo ser actualizado"));
    CsvReport report = new CsvReport();
    byte[] resultExpected = (String.format("%s,%s%n", HEADERS[0], HEADERS[1]) +
        String.format("1,Actualizado correctamente%n") +
        String.format("2,No pudo ser actualizado%n")).getBytes(StandardCharsets.UTF_8);

    byte[] result = report.convertToCSV(rows);

    assertThat(result).isNotNull().isEqualTo(resultExpected);
  }
}