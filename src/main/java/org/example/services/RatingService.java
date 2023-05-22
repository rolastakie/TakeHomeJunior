package org.example.services;

import org.example.exceptions.FileNotFoundException;
import org.example.models.Rating;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RatingService {

    private RatingService() {

    }

    public static List<Rating> getRatingListFromFile(final String filePath) {
        List<Rating> ratingList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                ratingList.add(new Rating(values[0], values[1], values[2]));
            }

        } catch (IOException e) {
            throw new FileNotFoundException(filePath);
        }
        return ratingList;
    }

    public static String getRatingById(List<Rating> ratingRecordsList, String movieId) {
        double ratingSum = 0;
        int ratingCount = 0;
        DecimalFormat f = new DecimalFormat("#0.00");
        for (Rating ratingRecord : ratingRecordsList) {
            if (ratingRecord.getMovieId().equals(movieId)) {
                ratingSum += Double.parseDouble(ratingRecord.getUserRating());
                ratingCount++;

            }
        }
        if (ratingCount > 0) {
            return String.format(" %s bei %s Bewertungen.", f.format(ratingSum / ratingCount), ratingCount);
        } else {
            return "";
        }
    }
}
