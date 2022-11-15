package api.test2;

import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;
import api.models.PlayerModelToSignUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.requests.GetGuestTokenRequest;
import api.requests.SignUpNewPlayerRequest;
import api.steps.StepExtractData;

import static org.hamcrest.Matchers.hasKey;

public class SigningUpNewPlayerTest {
        //инициализируем необходимые для тестов экземпляры классов
        GetGuestTokenRequest getGuestTokenRequest = new GetGuestTokenRequest();
        StepExtractData stepExtractData = new StepExtractData();
        GenerateData generateData = new GenerateData();
        SignUpNewPlayerRequest signUpNewPlayerRequest = new SignUpNewPlayerRequest();
        PlayerModelToSignUp bodyWithRequiredFields = generateData.getBodyWithRequiredFields();

    @DisplayName("проверка Status line")
    @Test public void testStatusLine() {
        //получаем респонс
        ValidatableResponse response = getGuestTokenRequest.getNewToken();
        //извлекаем access token из респонса
        String extractedAccessToken = stepExtractData.getAccessTokenFromResponse(response);
        //получаем респонс после создания нового игрока и оборачиваем его в валидируемый респонс
        ValidatableResponse response2 = signUpNewPlayerRequest.signUpNewPlayerWitModel(extractedAccessToken, bodyWithRequiredFields);
        //проверка statusLine
        response2.statusLine("HTTP/1.1 201 Created");
    }

    @DisplayName("проверка Status code")
    @Test
    public void testResponseStatus () {
        //получаем респонс
        ValidatableResponse response = getGuestTokenRequest.getNewToken();
        //извлекаем access token из респонса
        String extractedAccessToken = stepExtractData.getAccessTokenFromResponse(response);
        //получаем респонс после создания нового игрока и оборачиваем его в валидируемый респонс
        ValidatableResponse response2 = signUpNewPlayerRequest.signUpNewPlayerWitModel(extractedAccessToken, bodyWithRequiredFields);
        //проверка статуса кода
        response2.log().ifError().statusCode(201);
    }

    @DisplayName("проверка наличия всех ключей/полей в теле респонса")
    @Test public void testKeysOfBodyFields () {
        //получаем респонс
        ValidatableResponse response = getGuestTokenRequest.getNewToken();
        //извлекаем access token из респонса
        String extractedAccessToken = stepExtractData.getAccessTokenFromResponse(response);
        //получаем респонс после создания нового игрока и оборачиваем его в валидируемый респонс
        ValidatableResponse response2 = signUpNewPlayerRequest.signUpNewPlayerWitModel(extractedAccessToken, bodyWithRequiredFields);

        // проверка наличия всех полей/ключей по их имени
        response2.body("$", hasKey("id"))
                .body("$", hasKey("country_id"))
                .body("$", hasKey("timezone_id"))
                .body("$", hasKey("username"))
                .body("$", hasKey("email"))
                .body("$", hasKey("name"))
                .body("$", hasKey("surname"))
                .body("$", hasKey("gender"))
                .body("$", hasKey("phone_number"))
                .body("$", hasKey("birthdate"))
                .body("$", hasKey("bonuses_allowed"))
                .body("$", hasKey("is_verified"));
    }
}
