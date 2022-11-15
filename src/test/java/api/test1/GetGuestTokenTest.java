package api.test1;

import api.requests.GetGuestTokenRequest;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class GetGuestTokenTest {
    GetGuestTokenRequest getGuestTokenRequest = new GetGuestTokenRequest();

    @Test
    public void getGuestTokenStatusCodeTest () {
        ValidatableResponse receivedResponse = getGuestTokenRequest.getNewToken();
        //проверка
        receivedResponse.assertThat().statusCode(200);
    }

    @Test
    public void accessTokenFieldIsNotEmptyAndIsNotBlank() {
        ValidatableResponse receivedResponse = getGuestTokenRequest.getNewToken();
        //проверка
        receivedResponse.body("access_token", notNullValue());
        receivedResponse.body("access_token", not(""));
    }
}
