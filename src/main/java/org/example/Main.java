package org.example;

import java.io.*;
import java.text.DecimalFormat;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NullPointerException, RuntimeException {
        System.out.println("Hello world!");
        String COMMA_DELIMITER = ",";
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Dateien/movies.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(   COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }

            System.out.println("Bitte geben Sie die Filmnummer ein, zu der Sie eine Bewertung wünschen");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String name = reader.readLine();

            if (name != "") {
                for (List<String> record : records) {
                    String gefundenerRecord = "keiner";
                    if (record.toArray()[0].equals(name)) {
                        System.out.println("Es wurde der Film: " + record.toArray()[1] + " ausgewählt.");
                        gefundenerRecord = record.toArray()[0].toString();
                    }

            if (gefundenerRecord.equals("keiner")) {
                // nichts zu tun
            } else {
                List<List<String>> records2 = new ArrayList<>();
                try (BufferedReader br2 = new BufferedReader(new FileReader("Dateien/ratings.csv"))) {
                    String zeile;
                    while ((zeile = br2.readLine()) != null) {
                        String[] values = zeile.split(  COMMA_DELIMITER);
                        records2.add(Arrays.asList(values));
                    }

                            System.out.println(gefundenerRecord);
                    double gesamt = 0;
                    int iterator = 0;
                    for (List<String> record2 : records2) {
                        if (record2.toArray()[1].toString().equals(gefundenerRecord)) {
                            gesamt = gesamt + Double.parseDouble(record2.toArray()[2].toString());
                            ++iterator;
                        }
                    }
                    DecimalFormat f = new DecimalFormat("#0.00");
                    System.out.println("Die Bewertung für den Film ist: " + f.format(gesamt / iterator));

                } catch (Exception e) {
                    throw e;
                }
            }
                }
            }
        } catch (Exception e) {
            System.out.println("No File Found");
        }
    }
}