package api.requests;
import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class AuthorizationPlayerRequest {
    private final String basicAuthUsername = "front_2d6b0a8391742f5d789d7d915755e09e";
    GenerateData generateData = new GenerateData();
    private final String getBody(String username, String password) {
        return "{" +
                "\"grant_type\":\"password\"," +
                "\"username\":\"" + username + "\"," +
                "\"password\":\"" + password + "\"" +
                "}";
    }

    public ValidatableResponse authorizationAsPlayer (String username, String password) {
        return given().
                auth()
                .preemptive()
                .basic(basicAuthUsername, "")
                .header("Content-Type", "application/json")
                .body(this.getBody(username, password))
                .post(generateData.getBASE_URL() + "/v2/oauth2/token")
                .then();
    }
}
