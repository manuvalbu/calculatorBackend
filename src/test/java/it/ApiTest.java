package it;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sopra.calculator.CalculatorApplication;
import com.sopra.calculator.business.exception.NullNumberException;
import com.sopra.calculator.business.port.ICalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = {CalculatorApplication.class})
@AutoConfigureMockMvc
class ApiTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ICalculatorService calculatorServiceMock;
  Double sumAddend1 = Math.random() * 100;
  Double sumAddend2 = Math.random() * 100;
  String sumPathParameters = "/" + sumAddend1 + "/" + sumAddend2;
  String pathSum = "/sum" + sumPathParameters;
  Double subtrahend1 = Math.random() * 100;
  Double subtrahend2 = Math.random() * 100;
  String subPathParameters = "/" + subtrahend1 + "/" + subtrahend2;
  String pathSub = "/sub" + subPathParameters;

  @Test
  void sum2numbersOk_IT() throws Exception {
    given(calculatorServiceMock.calculate2NumbersSum(any(Double.class),
        any(Double.class))).willReturn(
        sumAddend1 + sumAddend2);

    this.mockMvc.perform(MockMvcRequestBuilders.
            get(pathSum))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(Double.toString(sumAddend1 + sumAddend2)));

    verify(calculatorServiceMock, times(1)).calculate2NumbersSum(sumAddend1, sumAddend2);

  }

  @Test
  void sub2numbersOk_IT() throws Exception {
    given(calculatorServiceMock.calculate2NumbersSub(any(Double.class),
        any(Double.class))).willReturn(
        subtrahend1 - subtrahend2);

    this.mockMvc.perform(MockMvcRequestBuilders.
            get(pathSub))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(Double.toString(subtrahend1 - subtrahend2)));

    verify(calculatorServiceMock, times(1)).calculate2NumbersSub(subtrahend1, subtrahend2);

  }

  @Test
  void badParameterFails_IT() throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders.
            get("/sum/12/"))
        .andDo(print())
        .andExpect(status().isNotFound());

    verify(calculatorServiceMock, times(0)).calculate2NumbersSum(any(Double.class),
        any(Double.class));

    this.mockMvc.perform(MockMvcRequestBuilders.
            get("/sub/12/a"))
        .andDo(print())
        .andExpect(status().isBadRequest());

    verify(calculatorServiceMock, times(0)).calculate2NumbersSub(any(Double.class),
        any(Double.class));
  }

  @Test
  void nullNumberInServiceFails_IT() throws Exception {
    given(
        calculatorServiceMock.calculate2NumbersSum(any(Double.class), any(Double.class))).willThrow(
        NullNumberException.class);

    this.mockMvc.perform(MockMvcRequestBuilders.
            get(pathSum))
        .andDo(print())
        .andExpect(status().isBadRequest());

    verify(calculatorServiceMock, times(1)).calculate2NumbersSum(sumAddend1, sumAddend2);

    given(
        calculatorServiceMock.calculate2NumbersSub(any(Double.class), any(Double.class))).willThrow(
        NullNumberException.class);

    this.mockMvc.perform(MockMvcRequestBuilders.
            get(pathSub))
        .andDo(print())
        .andExpect(status().isBadRequest());

    verify(calculatorServiceMock, times(1)).calculate2NumbersSub(subtrahend1, subtrahend2);

  }
}
