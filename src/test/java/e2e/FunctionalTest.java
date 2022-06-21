package e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sopra.calculator.CalculatorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = {
    CalculatorApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FunctionalTest {

  @LocalServerPort
  int randomServerPort;

  RestTemplate restTemplate = new RestTemplate();

  Double sumAddend1 = Math.random() * 100;
  Double sumAddend2 = Math.random() * 100;
  String sumPathParameters = "/" + sumAddend1 + "/" + sumAddend2;
  String pathSum = "/sum" + sumPathParameters;
  Double subtrahend1 = Math.random() * 100;
  Double subtrahend2 = Math.random() * 100;
  String subPathParameters = "/" + subtrahend1 + "/" + subtrahend2;
  String pathSub = "/sub" + subPathParameters;

  @Test
  void Sum2NumbersOk() {
    final String uri = "http://localhost:" + randomServerPort + pathSum;
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals(String.valueOf(sumAddend1 + sumAddend2), responseEntity.getBody());
  }

  @Test
  void Sub2NumbersOk() {
    final String uri = "http://localhost:" + randomServerPort + pathSub;
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals(String.valueOf(subtrahend1 - subtrahend2), responseEntity.getBody());
  }

}
