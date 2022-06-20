package com.sopra.calculator.business.service;

import com.sopra.calculator.business.domain.Calculator;
import com.sopra.calculator.business.exception.NullNumberException;
import com.sopra.calculator.business.port.ICalculatorService;
import io.corp.calculator.TracerImpl;
import org.springframework.stereotype.Service;

/**
 * Service for using Calculator, in sum operation for now
 */
@Service
public class CalculatorService implements ICalculatorService {

  TracerImpl tracer;

  public CalculatorService() {
    this.tracer = new TracerImpl();
  }

  public Double calculate2NumbersSum(Double a, Double b) {
    if (a == null || b == null) {
      throw new NullNumberException("Not given value in any of the input numbers");
    }
    Calculator calculator = new Calculator();
    String expression = a + "+" + b;
    Double result = calculator.evaluateExpression(expression, false);
    tracer.trace(result);
    return result;
  }
}
