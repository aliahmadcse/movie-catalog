package codes.aliahmad.moviecatalogservice.resource;


import codes.aliahmad.commons.models.CatalogItem;
import codes.aliahmad.commons.models.Movie;
import codes.aliahmad.commons.models.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class MovieCatalogResource
{
  private final RestTemplate restTemplate;

  @GetMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
  {
    List<Rating> ratings = List.of(
            new Rating("1234", 4),
            new Rating("5678", 5)
    );

    return ratings.stream().map(rating -> {
      Movie movie = restTemplate.getForObject("http://localhost:8181/api/v1/movie/" + rating.getMovieId(), Movie.class);
      return new CatalogItem(movie.getName(), "Desc", rating.getRating());
    }).toList();
  }
}
