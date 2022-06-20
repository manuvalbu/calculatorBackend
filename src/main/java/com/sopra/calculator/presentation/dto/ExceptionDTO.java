package com.sopra.calculator.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ExceptionDTO {

  private String code;
  private String message;

}
