package api.steps;
import io.restassured.response.ValidatableResponse;

public class StepExtractData {

    public String getAccessTokenFromResponse(ValidatableResponse response) {
        return response
                .extract()
                .path("access_token");
    }


}
