package codes.aliahmad.movieinfoservice.resource;


import codes.aliahmad.commons.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieResource
{

  @GetMapping("/{movieId}")
  public Movie getMovieInfo(@PathVariable("movieId") String movieId)
  {
    return new Movie(movieId, "Test name");
  }
}
