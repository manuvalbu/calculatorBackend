package ut.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sopra.calculator.business.exception.NullNumberException;
import com.sopra.calculator.business.port.ICalculatorService;
import com.sopra.calculator.business.service.CalculatorService;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

  @Test
  void sum2PositiveNumbers_UT() {
    ICalculatorService calculatorService = new CalculatorService();
    Double a = Math.random() * 100;
    Double b = Math.random() * 100;
    Double result = calculatorService.calculate2NumbersSum(a, b);
    assertEquals(a + b, result);
  }

  @Test
  void sum2PositiveAndNegativeNumbers_UT() {
    ICalculatorService calculatorService = new CalculatorService();
    Double a = Math.random() * 100;
    Double b = Math.random() * 100 - 100;
    Double result = calculatorService.calculate2NumbersSum(a, b);
    assertEquals(a + b, result);
    a = Math.random() * 100 - 100;
    b = Math.random() * 100;
    result = calculatorService.calculate2NumbersSum(a, b);
    assertEquals(a + b, result);
  }

  @Test
  void sum2NegativeNumbers_UT() {
    ICalculatorService calculatorService = new CalculatorService();
    Double a = Math.random() * 100 - 100;
    Double b = Math.random() * 100 - 100;
    Double result = calculatorService.calculate2NumbersSum(a, b);
    assertEquals(a + b, result);
  }

  @Test
  void sum2NumbersNullFail_UT() {
    ICalculatorService calculatorService = new CalculatorService();
    Double nullNumber = null;
    Double number = Math.random() * 100;
    assertThrows(NullNumberException.class,
        () -> calculatorService.calculate2NumbersSum(nullNumber, number));
    assertThrows(NullNumberException.class,
        () -> calculatorService.calculate2NumbersSum(number, nullNumber));
    assertThrows(NullNumberException.class,
        () -> calculatorService.calculate2NumbersSum(nullNumber, nullNumber));
  }
}
