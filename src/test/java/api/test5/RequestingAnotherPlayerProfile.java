package api.test5;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.requests.GetSinglePlayerProfileRequest;
import api.steps.StepGetAuthAccessToken;
import java.util.List;

public class RequestingAnotherPlayerProfile {

    @DisplayName("Запрос данных другого игрока")
    @Test
    public void requestAnotherPlayersData() {
            StepGetAuthAccessToken stepGetAuthAccessToken = new StepGetAuthAccessToken();
            StepGetAuthAccessToken stepGetAuthAccessTokenOfAnotherPlayer = new StepGetAuthAccessToken();
            GetSinglePlayerProfileRequest getSinglePlayerProfileRequest = new GetSinglePlayerProfileRequest();
            //извлекаем в List<Object> bearerToken и body использованный при создании player и его авторизации
            List<Object> listWithTokenAndBody = stepGetAuthAccessToken.getAccessTokenIdAndBodyDetailsFromAuthorizedPlayer();
            List<Object> listWithTokenAndBodyOfAnotherPlayer = stepGetAuthAccessTokenOfAnotherPlayer.getAccessTokenIdAndBodyDetailsFromAuthorizedPlayer();
            //приводим извлеченный токен игрока #1 в тип String
            String bearerToken = listWithTokenAndBody.get(0).toString();
            //приводим извлеченный id номер игрока #2 в тип Integer
            Integer id = (Integer) listWithTokenAndBodyOfAnotherPlayer.get(1);
            //намеренно отправляем запрос с токеном от игрока #1, и id номером от игрока #2.
            //важно, что и id и токен взяты от существуюших игроков
            ValidatableResponse receivedResponse = getSinglePlayerProfileRequest.getPlayerProfileData(bearerToken, id);
            //проверка status code
            receivedResponse.assertThat().statusCode(404);
    }
}
