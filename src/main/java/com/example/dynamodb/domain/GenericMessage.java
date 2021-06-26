package com.example.dynamodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class GenericMessage {
  private LocalDateTime timestamp;
  private Integer status;
  private String error;
  private String path;
}
