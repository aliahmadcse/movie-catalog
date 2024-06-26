package codes.aliahmad.ratingsdataservice.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config/test")
@RefreshScope
public class ConfigServerTestResource
{
  @Value("${greeting}")
  private String greeting;

  @GetMapping
  public String getGreeting()
  {
    return greeting;
  }
}
