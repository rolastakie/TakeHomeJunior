package org.example.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rating {
    String userId;
    String movieId;
    String userRating;

    public Rating(String userId, String movieId, String userRating) {
        this.userId = userId;
        this.movieId = movieId;
        this.userRating = userRating;
    }

}
