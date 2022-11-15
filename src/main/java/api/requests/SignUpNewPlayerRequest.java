package api.requests;

import api.models.PlayerModelToSignUp;
import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class SignUpNewPlayerRequest {
    GenerateData generateData = new GenerateData();

    public ValidatableResponse signUpNewPlayerWitModel(String bearerToken, PlayerModelToSignUp body) {

        return given()
                .headers("Authorization",
                        "Bearer " + bearerToken,
                        "Content-Type", "application/json"
                )
                .body(body)
                .post(generateData.getBASE_URL() + "/v2/players")
                .then();
    }
}
