package api.steps;

import api.models.PlayerModelToSignUp;
import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;
import api.requests.AuthorizationPlayerRequest;
import api.requests.GetGuestTokenRequest;
import api.requests.SignUpNewPlayerRequest;

import java.util.ArrayList;
import java.util.List;

public class StepGetAuthAccessToken {

    public List<Object> getAccessTokenIdAndBodyDetailsFromAuthorizedPlayer() {
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
        ValidatableResponse response = signUpNewPlayerRequest.signUpNewPlayerWitModel(accessToken, bodyWithRequiredFields);
        //получаем username и пароль только что авторизованного игрока
        ValidatableResponse response3 = authorizationPlayerRequest.authorizationAsPlayer(bodyWithRequiredFields.getUsername(),
                bodyWithRequiredFields.getPasswordRepeat());
        //инициализируем ArrayList
        List<Object> listWithTokenAndIdAndBody = new ArrayList<Object>();
        //ложим access token и body респонса
        listWithTokenAndIdAndBody.add(response3.extract().path("access_token").toString());
        listWithTokenAndIdAndBody.add(response.extract().body().path("id"));
        listWithTokenAndIdAndBody.add(bodyWithRequiredFields);

        return listWithTokenAndIdAndBody;
    }
}
