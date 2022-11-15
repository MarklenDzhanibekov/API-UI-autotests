package api.requests;
import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class GetSinglePlayerProfileRequest {

 GenerateData generateData = new GenerateData();

    public ValidatableResponse getPlayerProfileData(String bearerToken, Integer id) {
         ValidatableResponse response =
                given()
                        .header("Authorization", "Bearer " + bearerToken)
                        .pathParam("id", id)
                        .get(generateData.getBASE_URL() + "/v2/players/{id}")
                        .then();
        return response;
    }
}
