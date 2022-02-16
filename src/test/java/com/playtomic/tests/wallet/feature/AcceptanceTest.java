package com.playtomic.tests.wallet.feature;

import com.playtomic.tests.wallet.WalletApplication;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {WalletApplication.class})
@ExtendWith(SpringExtension.class)
public class AcceptanceTest {

  @LocalServerPort private Integer serverPort = 0;

  @BeforeEach
  void setUp() {
    RestAssured.port = serverPort;
    RestAssured.defaultParser = Parser.JSON;
  }
}
