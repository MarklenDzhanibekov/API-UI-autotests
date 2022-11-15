package api.steps;
import api.models.PlayerModelToSignUp;
import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;
import api.requests.GetGuestTokenRequest;
import api.requests.SignUpNewPlayerRequest;

public class StepGetPswAndUsernameOfRegisteredPlayer {

    public String[] getPasswordAndUsernameFromBody() {
        //создаем необходимые экземпляры классов
        GenerateData generateData = new GenerateData();
        GetGuestTokenRequest getGuestTokenRequest = new GetGuestTokenRequest();
        SignUpNewPlayerRequest signUpNewPlayerRequest = new SignUpNewPlayerRequest();
        StepExtractData stepExtractData = new StepExtractData();

        //получаем респонс с access token внутри
        ValidatableResponse response1 = getGuestTokenRequest.getNewToken();
        //извлечение access token из респонса
        String accessToken = stepExtractData.getAccessTokenFromResponse(response1);
        //генерируем body для регистрирования нового игрока
        PlayerModelToSignUp bodyWithRequiredFields = generateData.getBodyWithRequiredFields();
        //получение респонса после регистрации нового игрока
        ValidatableResponse response2 = signUpNewPlayerRequest.signUpNewPlayerWitModel(accessToken, bodyWithRequiredFields);
        //ложим в массив пароль и username созданного игрока
        String[] arrayWithPswAndUsername = {bodyWithRequiredFields.getPasswordRepeat(), bodyWithRequiredFields.getUsername()};

        return arrayWithPswAndUsername;
    }
}
