package org.example.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Movie {
    String id;
    String title;
    String genres;

    public Movie(String id, String title, String genres) {
        this.id = id;
        this.title = title;
        this.genres = genres;
    }
}


