package com.playtomic.tests.wallet.feature;

import com.playtomic.tests.wallet.infrastructure.db.jpa.JPATransactionRepository;
import com.playtomic.tests.wallet.infrastructure.db.jpa.JPAWalletRepository;
import com.playtomic.tests.wallet.infrastructure.db.jpa.TransactionEntity;
import com.playtomic.tests.wallet.infrastructure.db.jpa.WalletEntity;
import com.playtomic.tests.wallet.infrastructure.payment.stripe.StripeService;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import javax.inject.Inject;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class GetWalletAcceptanceTest extends AcceptanceTest {

    @Inject
    private JPAWalletRepository jpaWalletRepository;

    @Inject
    private JPATransactionRepository jpaTransactionRepository;

    @MockBean
    private StripeService stripeService;

    @Test
    void should_obtain_current_wallet() throws JSONException {

        String id = UUID.randomUUID().toString();
        String idTransaction = UUID.randomUUID().toString();

        int balance = 100;

        jpaWalletRepository.save(WalletEntity.of(id, balance));


        Response walletResponse = given()
                .header("content-type", "application/json")
                .get("/api/v1/wallets/" + id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .body("$", notNullValue())
                .extract().response();

        JSONObject content = new JSONObject(walletResponse.getBody().asString());

        assertThat(content.getString("id")).isEqualTo(id);
        assertThat(content.getInt("balance")).isEqualTo(balance);

        given()
                .header("content-type", "application/json")
                .body("{\"id\": \"" + idTransaction + "\",\"card_number\": \"1234567812345678\", \"balance_to_add\": 50}")
                .put("/api/v1/wallets/" + id + "/add-balance")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());


        WalletEntity byId = jpaWalletRepository.findById(id).get();
        TransactionEntity transaction = jpaTransactionRepository.findById(idTransaction).get();
        assertThat(byId.getBalance()).isEqualTo(150);
        assertThat(transaction.getId()).isEqualTo(idTransaction);
    }
}
