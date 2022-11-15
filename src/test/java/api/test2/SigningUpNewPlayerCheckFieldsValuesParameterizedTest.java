package api.test2;
import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;
import api.models.PlayerModelToGetResponse;
import api.models.PlayerModelToSignUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import api.requests.GetGuestTokenRequest;
import api.requests.SignUpNewPlayerRequest;
import api.steps.StepExtractData;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SigningUpNewPlayerCheckFieldsValuesParameterizedTest {
    @DisplayName("Проверка значения полей при регистрации нового игрока")
    @ParameterizedTest (name = "{index} #1 все обяз.поля" +
            " #2 поле firstname" +
            " #3 поле surname" +
            " #4 поле currency_code")
    @MethodSource("provideDifferentRequestBodies")
    void testFieldsValues(PlayerModelToSignUp bodyWithTestedFields) {
        //инициализируем необходимые для теста экземпляры классов
        GetGuestTokenRequest getGuestTokenRequest = new GetGuestTokenRequest();
        StepExtractData stepExtractData = new StepExtractData();
        SignUpNewPlayerRequest signUpNewPlayerRequest = new SignUpNewPlayerRequest();
        //получаем респонс
        ValidatableResponse response = getGuestTokenRequest.getNewToken();
        //извлекаем access token из респонса
        String extractedAccessToken = stepExtractData.getAccessTokenFromResponse(response);
        //отправляем запрос на регистрацию нового игрока
        ValidatableResponse receivedResponse = signUpNewPlayerRequest.signUpNewPlayerWitModel(extractedAccessToken, bodyWithTestedFields);
        //создаем десериализованный объект, чтобы использовать его в проверках и уменьшить объем кода
        PlayerModelToGetResponse deserializedResponse = receivedResponse.extract().body().as(PlayerModelToGetResponse.class);

        //Проверки
        //Тут проверяется действительно ли данные отправленные для регистрации игрока совпадают с данными,
        //которые возвращает endpoint.
        receivedResponse.statusCode(201);
        // Проверяются обязательные поля userName, email
        assertEquals(deserializedResponse.getUsername(),
                bodyWithTestedFields.getUsername(), "поле 'userName' не совпадает");
        assertEquals(deserializedResponse.getEmail(),
                bodyWithTestedFields.getEmail(), "поле 'email' не совпадает");
        //проверка value поля "name". Так как оно optional, то необходима сначала проверка на его наличие.
        if (!(deserializedResponse.getName() == null)) {
            assertEquals(deserializedResponse.getName(),
                    bodyWithTestedFields.getName(), "поле 'name' не совпадает");
        }
            //проверка value "surname". Так как оно optional, то необходима сначала проверка на его наличие.
        if (!(deserializedResponse.getSurname() == null)) {
            assertEquals(deserializedResponse.getSurname(),
                    bodyWithTestedFields.getSurname(), "поле 'surname' не совпадает");
        }
    }

        // метод с тестируемыми данными (аргументы)
        private static Stream<Arguments> provideDifferentRequestBodies () {
            GenerateData generateData = new GenerateData();
            PlayerModelToSignUp bodyWithRequiredFields = generateData.getBodyWithRequiredFields();
            PlayerModelToSignUp bodyWithUsersFirstNameOptionalField = generateData.getBodyWithUsersFirstNameOptionalField();
            PlayerModelToSignUp bodyWithUsersSurNameOptionalField = generateData.getBodyWithUsersSurNameOptionalField();
            PlayerModelToSignUp bodyWithCurrencyCodeOptionalField = generateData.getBodyWithCurrencyCodeOptionalField();
            return Stream.of(
                    Arguments.of(bodyWithRequiredFields),
                    Arguments.of(bodyWithUsersFirstNameOptionalField),
                    Arguments.of(bodyWithUsersSurNameOptionalField),
                    Arguments.of(bodyWithCurrencyCodeOptionalField)
            );
        }
    }
