package ut.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sopra.calculator.business.domain.Calculator;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  @Test
  void calculateExpressionSum2Numbers_UT() {
    String expression = "4+(-3)";
    Calculator calculator = new Calculator();
    Double result = calculator.evaluateExpression(expression, false);
    assertEquals(4 - 3, result);
    assertEquals(0, calculator.getMem());
  }

  @Test
  void calculateExpressionSum2NumbersMem_UT() {
    Double initialMem = 1.0;
    String expression1 = "4+(-3)";
    Calculator calculator = new Calculator(initialMem);
    assertEquals(initialMem, calculator.getMem());
    Double result1 = calculator.evaluateExpression(expression1, true);
    assertEquals(4 - 3, result1);
    assertEquals(result1, calculator.getMem());
    String expression2 = "-2+(-7)+mem";
    Double result2 = calculator.evaluateExpression(expression2, true);
    assertEquals(-2 - 7 + result1, result2);
    assertEquals(result2, calculator.getMem());
    calculator.resetMem();
    assertEquals(0, calculator.getMem());
  }

  @Test
  void calculateBadExpressionUnknownVariableFail_UT() {
    String expression = "4+r";
    Calculator calculator = new Calculator();
    assertThrows(UnknownFunctionOrVariableException.class,
        () -> calculator.evaluateExpression(expression, false));
  }

  @Test
  void calculateBadExpressionBadArgumentFail_UT() {
    String expression = "4 2";
    Calculator calculator = new Calculator();
    assertThrows(IllegalArgumentException.class,
        () -> calculator.evaluateExpression(expression, false));
  }
}
