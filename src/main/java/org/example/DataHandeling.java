package org.example;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataHandeling {
    private static final String BASE_URL = "https://data.gov.lv/dati/dataset/f44f8d2f-4121-4494-b009-368f48992603/resource/443d4936-2b81-40a0-9f95-932f5b480c3f/download/sertificetie-buvspecialisti-";

    public List<Person> loadCsvFromUrl() throws Exception {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        URL csvUrl = new URL(BASE_URL + currentDate + ".csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(csvUrl.openStream(), StandardCharsets.UTF_8));

        return new CsvToBeanBuilder<Person>(reader)
                .withType(Person.class)
                .withSkipLines(1)
                .build()
                .parse();
    }
}