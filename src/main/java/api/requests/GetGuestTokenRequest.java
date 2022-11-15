package api.requests;

import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetGuestTokenRequest {
    private final String basicAuthUsername = "front_2d6b0a8391742f5d789d7d915755e09e";
    private final String body =
            "{\"grant_type\":\"client_credentials\"," +
            "\"scope\":\"guest:default\"" +
            "}";
    GenerateData generateData = new GenerateData();

    public ValidatableResponse getNewToken() {
        return given()
                .auth()
                .preemptive()
                .basic(basicAuthUsername, "")
                .header("Content-Type", "application/json")
                .body(body)
                .post(generateData.getBASE_URL() + "/v2/oauth2/token")
                .then();
    }

}

