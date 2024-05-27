package codes.aliahmad.ratingsdataservice.resource;


import codes.aliahmad.commons.models.Rating;
import codes.aliahmad.commons.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingResource
{
  @GetMapping("/{movieId}")
  public Rating getRatingByMovieId(@PathVariable("movieId") String movieId) {
    return new Rating(movieId, 4);
  }

  @GetMapping("/user/{userId}")
  public UserRating getRatingByUserId(@PathVariable("userId") String userId) {
    return new UserRating(List.of(
            new Rating("1234", 4),
            new Rating("5678", 5)
    ));
  }

}
