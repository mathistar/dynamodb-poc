package com.example.dynamodb.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ValidationGenericMessage extends GenericMessage {
  private Map<String, String> fieldError;

  @Builder
  public ValidationGenericMessage(LocalDateTime timestamp, Integer status, String error,
                                  String path,
                                  Map<String, String> fieldError) {
    super(timestamp, status, error, path);
    this.fieldError = fieldError;
  }
}
