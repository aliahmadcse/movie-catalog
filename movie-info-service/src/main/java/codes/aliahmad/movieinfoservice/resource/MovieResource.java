package codes.aliahmad.movieinfoservice.resource;


import codes.aliahmad.commons.models.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieResource
{

  private final RestTemplate restTemplate;

  @GetMapping("/{movieId}")
  public Movie getMovieInfo(@PathVariable("movieId") String movieId)
  {
    String response = restTemplate.getForObject("https://dummyjson.com/products/1", String.class);

    return new Movie(movieId, "Test name");
  }
}
