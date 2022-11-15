package api.steps;

import api.models.PlayerModelToSignUp;
import api.requests.AuthorizationPlayerRequest;
import api.requests.GetGuestTokenRequest;
import api.requests.SignUpNewPlayerRequest;
import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;

public class StepGetIdAndTokenOfPlayer {
    public List<Object> getPasswordAndUsernameFromBody() {
        //создаем необходимые экземпляры классов
        GenerateData generateData = new GenerateData();
        GetGuestTokenRequest getGuestTokenRequest = new GetGuestTokenRequest();
        SignUpNewPlayerRequest signUpNewPlayerRequest = new SignUpNewPlayerRequest();
        StepExtractData stepExtractData = new StepExtractData();
        AuthorizationPlayerRequest authorizationPlayerRequest = new AuthorizationPlayerRequest();

        //получаем респонс с access token внутри
        ValidatableResponse response1 = getGuestTokenRequest.getNewToken();
        //извлечение access token из респонса
        String accessToken = stepExtractData.getAccessTokenFromResponse(response1);
        //генерируем body для регистрирования нового игрока
        PlayerModelToSignUp bodyWithRequiredFields = generateData.getBodyWithRequiredFields();
        //получение респонса после регистрации нового игрока
        ValidatableResponse response2 = signUpNewPlayerRequest.signUpNewPlayerWitModel(accessToken, bodyWithRequiredFields);
        //получение респонса после авторизации под созданым игроком
        ValidatableResponse response3 = authorizationPlayerRequest.authorizationAsPlayer(bodyWithRequiredFields.getUsername(),bodyWithRequiredFields.getPasswordRepeat());
        //ложим в спиоок пароль и username созданного игрока
        List<Object> idAndToken = new ArrayList<>();
        idAndToken.add(response2.extract().body().path("id"));
        idAndToken.add(response3.extract().body().path("access_token"));

       return idAndToken;
    }
}
