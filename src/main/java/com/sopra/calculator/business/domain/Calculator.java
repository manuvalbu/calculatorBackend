package com.sopra.calculator.business.domain;

import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Calculator with memory that can support complex operations, but related to sum for now
 */
public class Calculator {

  private Double mem;

  public Calculator() {
    this.mem = 0.0;
  }

  public Calculator(Double mem) {
    this.mem = mem;
  }

  public Double getMem() {
    return mem;
  }

  public void resetMem() {
    this.mem = 0.0;
  }

  public Double evaluateExpression(String expression, boolean save) {
    Double result = new ExpressionBuilder(expression)
        .variable("mem")
        .build()
        .setVariable("mem", this.mem)
        .evaluate();
    if (save) {
      mem = result;
    }
    return result;
  }

}
