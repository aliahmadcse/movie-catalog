package codes.aliahmad.commons.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating
{
  private String movieId;
  private int rating;
}
