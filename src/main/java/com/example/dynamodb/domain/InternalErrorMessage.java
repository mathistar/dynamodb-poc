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
public class InternalErrorMessage extends GenericMessage {

  @Builder
  public InternalErrorMessage(LocalDateTime timestamp, Integer status, String error,
                              String path) {
    super(timestamp, status, error, path);
  }
}
