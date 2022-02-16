package com.playtomic.tests.wallet.feature;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetWallet extends AcceptanceTest {

  @Test
  void should_obtain_current_wallet() {

    String id = UUID.randomUUID().toString();
    int balance = 100;

    ResponseSpecification spec =
        new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.OK.value())
            .expectBody("wallet.id", equalTo(id))
            .expectBody("wallet.balance", equalTo(balance))
            .build();

    given()
        .header("content-type", "application/json")
        .get("/api/v1/wallets/" + id)
        .then()
        .spec(spec);
  }
}
