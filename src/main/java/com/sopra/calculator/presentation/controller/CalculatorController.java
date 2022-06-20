package com.sopra.calculator.presentation.controller;

import com.sopra.calculator.api.SumApiDelegate;
import com.sopra.calculator.business.port.ICalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CalculatorController implements SumApiDelegate {

  ICalculatorService calculatorService;

  public CalculatorController(ICalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }

  @Override
  public ResponseEntity<String> sumAddend1Addend2Get(Double addend1, Double addend2) {
    log.info("Calculator sum operation, addends : " + addend1 + " , " + addend2);
    Double result = calculatorService.calculate2NumbersSum(addend1, addend2);
    log.info("Calculator sum operation successful, result : " + result);
    return new ResponseEntity<>(String.valueOf(result), HttpStatus.OK);
  }

}
