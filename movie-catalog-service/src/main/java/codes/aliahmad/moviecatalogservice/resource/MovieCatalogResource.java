package codes.aliahmad.moviecatalogservice.resource;


import codes.aliahmad.commons.models.CatalogItem;
import codes.aliahmad.commons.models.Movie;
import codes.aliahmad.commons.models.UserRating;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class MovieCatalogResource
{
  private final RestTemplate restTemplate;
  private final CircuitBreakerFactory circuitBreakerFactory;

  @GetMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
  {
    return circuitBreakerFactory.create("delay").run(() -> {
      UserRating ratings = restTemplate.getForObject("http://ratings-data-service/api/v1/rating/user/" + userId, UserRating.class);

      return ratings.getRatings().stream().map(rating -> {
        // for each movie id, call movie info service and get details
        Movie movie = restTemplate.getForObject("http://movie-info-service/api/v1/movie/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), "Desc", rating.getRating());
      }).toList();
    }, throwable -> getFallbackCatalog(userId, throwable));
  }

  public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId, Throwable throwable)
  {
    return List.of(new CatalogItem("No movie", "", 0));
  }
}
