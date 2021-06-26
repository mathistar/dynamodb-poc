package com.example.dynamodb.converter;

import com.example.dynamodb.domain.InternalErrorMessage;
import com.example.dynamodb.domain.ValidationGenericMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestControllerAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleRequestBody(MethodArgumentNotValidException ex, HttpServletRequest request) {

    ValidationGenericMessage errorObject = ValidationGenericMessage.builder()
      .timestamp(LocalDateTime.now())
      .status(HttpStatus.BAD_REQUEST.value())
      .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
      .path(request.getServletPath())
      .fieldError(ex.getBindingResult().getFieldErrors().stream()
        .collect(Collectors.toMap(FieldError::getField, FieldError::getCode)))
      .build();

    log.error("400 Bad Request : ", ex);
    return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleInternalError(Exception ex,HttpServletRequest request) {

    InternalErrorMessage errorObject = InternalErrorMessage.builder()
      .timestamp(LocalDateTime.now())
      .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
      .path(request.getServletPath())
      .build();

    log.error("500 Internal Server Error : ", ex);
    return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
