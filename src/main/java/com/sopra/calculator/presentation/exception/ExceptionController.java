package com.sopra.calculator.presentation.exception;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import com.sopra.calculator.business.exception.NullNumberException;
import com.sopra.calculator.presentation.dto.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class ExceptionController {

  @ExceptionHandler({NullNumberException.class, IllegalArgumentException.class,
      UnknownFunctionOrVariableException.class, MethodArgumentTypeMismatchException.class})
  public ResponseEntity<ExceptionDTO> handleInputException(final Exception e) {
    ExceptionDTO exceptionDTO = ExceptionDTO
        .builder()
        .code(e.getClass().getName())
        .message(e.getMessage())
        .build();
    log.info(exceptionDTO.toString());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exceptionDTO);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ExceptionDTO> handleUnknownException(final Exception e) {
    ExceptionDTO exceptionDTO = ExceptionDTO
        .builder()
        .code(e.getClass().getName())
        .message(e.getMessage())
        .build();
    log.info(exceptionDTO.toString());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(exceptionDTO);
  }
}
