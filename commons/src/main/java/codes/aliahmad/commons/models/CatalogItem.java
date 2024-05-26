package codes.aliahmad.commons.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatalogItem
{
  private String title;
  private String description;
  private int rating;
}
