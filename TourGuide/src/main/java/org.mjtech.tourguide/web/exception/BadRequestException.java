package org.mjtech.tourguide.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * BadRequestException. a class that handle
 * a bad request
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
  public BadRequestException(String msg) {
    super(msg);
  }
}
