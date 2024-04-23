package org.example;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import com.opencsv.CSVWriter;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        try {
            List<Person> persons = new DataHandeling().loadCsvFromUrl();
            if (!persons.isEmpty()) {
                persons = persons.stream()
                        .filter(person -> "Aktīvs".equals(person.getStatus()))
                        .collect(Collectors.toList());

                persons.forEach(Person::convertDates);


                    Scanner userInput = new Scanner(System.in);

                    System.out.println("Ievadi 1 lai meklētu pēc uzvārda, vai ievadi 2 lai meklētu pēc sfēras nosaukuma:");
                    String searchText;
                    List<Person> searchResults;
                    switch (userInput.nextInt()) {
                        case 1:
                            System.out.println("Ievadi vārdu ko meklēt:");
                            searchText = userInput.next();
                            searchResults = searchPersonsBySurname(persons, searchText);
                            break;
                        case 2:
                            System.out.println("Ievadi sfēru pēc kuras meklēt:");
                            searchText = userInput.next();
                            searchResults = searchAreaOfActivityName(persons, searchText);
                            break;
                        default:
                            System.out.println("Neeksistējoša izvēle");
                            return;
                    }

                    try (CSVWriter writer = new CSVWriter(new FileWriter("output.csv"))) {
                        String[] header = { "Vārds", "Uzvārds", "Sertifikāta numurs",
                                "Sertifikāta piešķiršanas datums", "Sertifikāta aktuālais statuss",
                                "Darbības sfēras numurs",  "Darbības sfēras/jomas nosaukums",
                                "Aktuālās atbildīgās sertificēšanas institūcijas nosaukums","Aktuālās atbildīgās sertificēšanas institūcijas reģistrācijas numurs",
                                "Darbības sfēras piešķiršanas datums", "Sfēras aktuālais statuss"};

                        writer.writeNext(header);

                        for (Person person : searchResults) {
                            String[] data = { person.getName(), person.getSurname(), person.getSerificatNum(),
                                    person.getSertificateIssueDateStr(), person.getStatus(),
                                    person.getAreaOfActivityNum(),person.getAreaOfActivityName(),
                                    person.getResponsibleInstitutionName(), person.getResponsibleInstitutionsNum(),
                                    person.getAreaOfActivityIssueDateStr(),person.getAreaOfActivityStatus() };

                            writer.writeNext(data);
                        }
                    } catch (IOException e) {
                        System.out.println("Error with making csv file: " + e.getMessage());
                    }
            } else {
                throw new NoDataFound();
            }
        } catch (Exception e) {
            System.out.println("Something wrong with loading from csv "+e.getMessage());
        }
    }

    public static List<Person> searchPersonsBySurname(List<Person> persons, String searchText) {
        return persons.stream()
                .filter(person -> person.getSurname() != null && person.getSurname().toLowerCase().startsWith(searchText.toLowerCase()))
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());
    }
    public static List<Person> searchAreaOfActivityName(List<Person> persons, String searchText) {
        return persons.stream()
                .filter(person -> person.getAreaOfActivityName() != null && person.getAreaOfActivityName().toLowerCase().startsWith(searchText.toLowerCase()))
                .sorted(Comparator.comparing(Person::getAreaOfActivityName))
                .collect(Collectors.toList());
    }
}