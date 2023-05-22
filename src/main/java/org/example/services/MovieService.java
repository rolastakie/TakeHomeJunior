package org.example.services;

import org.example.exceptions.FileNotFoundException;
import org.example.models.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Utils.printString;

public class MovieService {

    private MovieService() {
    }

    public static String getMovie(List<Movie> movieRecordsList, String movieId) {
        for (Movie movieRecord : movieRecordsList) {
            if (movieRecord.getId().equals(movieId)) {
                printString(String.format("Es wurde der Film: %s ausgewählt.", movieRecord.getTitle()));
                printString(String.format(movieRecord.getGenres().contains("|") ? "Genres: %s" : "Genre: %s", movieRecord.getGenres()));
                return movieRecord.getId();
            }
        }
        return "";
    }

    public static List<Movie> getMovieListFromFile(final String filePath) {
        List<Movie> movieList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                movieList.add(new Movie(values[0], values[1], values[2]));
            }
        } catch (IOException e) {
            throw new FileNotFoundException(filePath);
        }
        return movieList;
    }
}
