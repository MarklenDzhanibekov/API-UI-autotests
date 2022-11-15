package api.test3;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.requests.AuthorizationPlayerRequest;
import api.steps.StepGetPswAndUsernameOfRegisteredPlayer;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

public class PlayerAuthorizationTest {
    AuthorizationPlayerRequest authorizationPlayerRequest = new AuthorizationPlayerRequest();

    @DisplayName("Ответ содержит токен и он не пустой")
    @Test
    public void isTokenInPlaceTest () {
        //использовали step чтобы получить пароль и username созданного пользователя
        StepGetPswAndUsernameOfRegisteredPlayer step = new StepGetPswAndUsernameOfRegisteredPlayer();
        //создали и наполнили массив новыми паролем и Username
        String[] pswAndUsername = step.getPasswordAndUsernameFromBody();
        //получаем респонс с после успешонго запроса на авторазацию созданного игрока
        ValidatableResponse response = authorizationPlayerRequest.authorizationAsPlayer(pswAndUsername[1], pswAndUsername[0]);
        //проверяем, что в теле респонса поле/ключ access_token существует и оно не пустое
        response.assertThat().body("$", hasKey("access_token"));
        response.assertThat().body("access_token", notNullValue());
    }

    @DisplayName("Респонс возвращает status code 200")
    public void isTokenAvailableTest () {
        //использовали step чтобы получить пароль и username созданного пользователя
        StepGetPswAndUsernameOfRegisteredPlayer step = new StepGetPswAndUsernameOfRegisteredPlayer();
        //создали и наполнили массив новыми паролем и Username
        String[] pswAndUsername = step.getPasswordAndUsernameFromBody();
        //получаем респонс с после успешонго запроса на авторазацию созданного игрока
        ValidatableResponse response = authorizationPlayerRequest.authorizationAsPlayer(pswAndUsername[1], pswAndUsername[0]);
        //проверяем, что получаем status code 200
        response.assertThat().statusCode(200);
    }
}
