package api.test4;

import io.restassured.response.ValidatableResponse;
import api.models.PlayerModelToGetResponse;
import api.models.PlayerModelToSignUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.requests.GetSinglePlayerProfileRequest;
import api.steps.StepGetAuthAccessToken;
import api.steps.StepGetIdAndTokenOfPlayer;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

public class getPlayerProfileDataTest {
    StepGetAuthAccessToken stepGetAuthAccessToken = new StepGetAuthAccessToken();
    GetSinglePlayerProfileRequest getSinglePlayerProfileRequest = new GetSinglePlayerProfileRequest();
    StepGetIdAndTokenOfPlayer stepGetIdAndTokenOfPlayer = new StepGetIdAndTokenOfPlayer();

    @DisplayName("Status code ответв = 200")
    @Test
    public void isStatusCodeEqual200Test() {
        List<Object> idAndToken = stepGetIdAndTokenOfPlayer.getPasswordAndUsernameFromBody();
        Integer id = (Integer) idAndToken.get(0);
        ValidatableResponse response = getSinglePlayerProfileRequest.getPlayerProfileData(idAndToken.get(1).toString(), id);
        response.assertThat().statusCode(200);
    }

    @DisplayName("Проверка Status line ответа")
    @Test
    public void isStatusLineCorrectTest() {
        List<Object> idAndToken = stepGetIdAndTokenOfPlayer.getPasswordAndUsernameFromBody();
        Integer id = (Integer) idAndToken.get(0);
        ValidatableResponse response = getSinglePlayerProfileRequest.getPlayerProfileData(idAndToken.get(1).toString(), id);
        response.assertThat().statusLine("HTTP/1.1 200 OK");
    }

    @DisplayName("Content-Type содержит ожидаемые параметры")
    @Test
    public void isContentTypeHasCorrectInfoTest() {
        List<Object> idAndToken = stepGetIdAndTokenOfPlayer.getPasswordAndUsernameFromBody();
        Integer id = (Integer) idAndToken.get(0);
        ValidatableResponse response = getSinglePlayerProfileRequest.getPlayerProfileData(idAndToken.get(1).toString(), id);
        response.assertThat().contentType("application/json;charset=UTF-8");
    }

    @DisplayName("Проверка ключей и их значений в теле ответа")
    @Test
    public void checkAllKeysAndValuesOffReceivedBody () {
        //извлекаем в List<Object> bearerToken и body использованный при создании player и его авторизации
        List<Object> listWithTokenAndBody = stepGetAuthAccessToken.getAccessTokenIdAndBodyDetailsFromAuthorizedPlayer();
        //приводим извлеченный токен в тип String и id в Integer
        String bearerToken = listWithTokenAndBody.get(0).toString();
        Integer id = (Integer) listWithTokenAndBody.get(1);
        //приводим извлеченное body в свой кастомный Pojo класс, чтобы можно было далее выполнять проверки
        PlayerModelToSignUp initialBody = PlayerModelToSignUp.class.cast(listWithTokenAndBody.get(2));
        //десериализуем ответ в объект для дальнейших проверок
        ValidatableResponse receivedResponse = getSinglePlayerProfileRequest.getPlayerProfileData(bearerToken, id);
        PlayerModelToGetResponse deserializedBody = receivedResponse.extract().body().as(PlayerModelToGetResponse.class);

        //Проверки
        //проверка значений в двух обязательных полях
        assertEquals(initialBody.getUsername(), deserializedBody.getUsername());
        assertEquals(initialBody.getEmail(), deserializedBody.getEmail());
        //проверка наличие ключей в теле
        assertThat(deserializedBody, hasProperty("id"));
        assertThat(deserializedBody, hasProperty("country_id"));
        assertThat(deserializedBody, hasProperty("timezone_id"));
        assertThat(deserializedBody, hasProperty("name"));
        assertThat(deserializedBody, hasProperty("surname"));
        assertThat(deserializedBody, hasProperty("gender"));
        assertThat(deserializedBody, hasProperty("phone_number"));
        assertThat(deserializedBody, hasProperty("birthdate"));
        assertThat(deserializedBody, hasProperty("bonuses_allowed"));
        assertThat(deserializedBody, hasProperty("gender"));
        assertThat(deserializedBody, hasProperty("is_verified"));
    }
}
