package org.example;

import org.example.exceptions.FileNotFoundException;
import org.example.models.Movie;
import org.example.models.Rating;
import org.example.services.MovieService;
import org.example.services.RatingService;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.example.utils.Utils.printString;

public class Main {

    public static void main(String[] args) throws RuntimeException {
        new Main();
    }

    public Main() {

        try {
            List<Movie> movieRecordsList = MovieService.getMovieListFromFile("Dateien/movies.csv");
            List<Rating> ratingRecordsList = RatingService.getRatingListFromFile("Dateien/ratings.csv");
            matchMovies(movieRecordsList, ratingRecordsList);
        } catch (FileNotFoundException e) {
            printString(e.getMessage());
        }
    }

    public void matchMovies(List<Movie> movieRecordsList,
                            List<Rating> ratingRecordsList) {
        if (!movieRecordsList.isEmpty() && !ratingRecordsList.isEmpty()) {
            printString("Bitte geben Sie die Filmnummer ein, zu der Sie eine Bewertung wissen wollen");
        }
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String movieNumber = scanner.nextLine();
        if (!movieNumber.equals("")) {
            String movieRecordId = MovieService.getMovie(movieRecordsList, movieNumber);
            if (!movieRecordId.equals("")) {
                String ratingString = RatingService.getRatingById(ratingRecordsList, movieRecordId);
                if (!ratingString.equals("")) {
                    printString(String.format("Die durchschnittliche Bewertung für den Film ist:%s", ratingString));
                } else {
                    printString("Es wurden keine Bewertungen für den Film abgebeben");
                }
            }
        }
    }

}