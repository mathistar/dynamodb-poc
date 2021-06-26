package com.example.dynamodb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "amazon")
@Getter
@Setter
public class AppConfiguration {

  private Aws aws;
  private Dynamodb dynamodb;

  @Getter
  @Setter
  public static class Aws {
    private String accessKey;
    private String region;
    private String secretKey;
  }

  @Getter
  @Setter
  public static class Dynamodb {
    private String endpoint;
  }
}
