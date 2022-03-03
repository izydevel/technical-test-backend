package com.playtomic.tests.wallet.feature;

import com.playtomic.tests.wallet.infrastructure.db.jpa.JPAWalletRepository;
import com.playtomic.tests.wallet.infrastructure.db.jpa.WalletEntity;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class GetWalletAcceptanceTest extends AcceptanceTest {

    @Inject
    private JPAWalletRepository jpaWalletRepository;

    @Test
    void should_obtain_current_wallet() throws JSONException {

        String id = UUID.randomUUID().toString();
        int balance = 100;

        jpaWalletRepository.save(WalletEntity.of(id, balance));


        Response response = given()
                .header("content-type", "application/json")
                .get("/api/v1/wallets/" + id)
                .then()
                .statusCode(200)
                .and()
                .body("$", notNullValue())
                .extract().response();

        JSONObject content = new JSONObject(response.getBody().asString());

        assertThat(content.getString("id")).isEqualTo(id);
        assertThat(content.getInt("balance")).isEqualTo(balance);
    }
}
