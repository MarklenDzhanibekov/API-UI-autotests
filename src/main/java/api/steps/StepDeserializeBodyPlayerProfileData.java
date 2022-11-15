package api.steps;

import api.models.PlayerModelToGetResponse;
import api.data.GenerateData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDeserializeBodyPlayerProfileData {
    GenerateData generateData = new GenerateData();

    public PlayerModelToGetResponse getPlayerProfileData(String bearerToken) {
        List<PlayerModelToGetResponse> playerModelToGetResponseList =
                given().log().all()
                        .header("Authorization", "Bearer " + bearerToken)
                        .get(generateData.getBASE_URL() + "/v2/players")
                        .then().extract().body().jsonPath().getList(".", PlayerModelToGetResponse.class);
        return playerModelToGetResponseList.get(0);
    }

    public PlayerModelToGetResponse getSinglePlayerProfile(String bearerToken, Integer id) {
        List<PlayerModelToGetResponse> playerModelToGetResponseList =
                given().log().all()
                        .header("Authorization", "Bearer " + bearerToken)
                        .pathParam("id", id)
                        .get(generateData.getBASE_URL() + "/v2/players/{id}")
                        .then().extract().body().jsonPath().getList(".", PlayerModelToGetResponse.class);
        return playerModelToGetResponseList.get(0);
    }
}
