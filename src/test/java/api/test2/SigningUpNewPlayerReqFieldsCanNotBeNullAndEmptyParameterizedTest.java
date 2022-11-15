package api.test2;

import api.data.GenerateData;
import io.restassured.response.ValidatableResponse;
import api.models.PlayerModelBuilder;
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

public class SigningUpNewPlayerReqFieldsCanNotBeNullAndEmptyParameterizedTest {
    @DisplayName("Проверка на Null и Пустоту для каждого обязательного поля при регистрации игрока")
    @ParameterizedTest (name = "{index} #1 username-Null" +
            " #2 username-Empty" +
            " #3 passwordChange-Null" +
            " #4 passwordChange-Empty" +
            " #5 passwordRepeat-Null" +
            " #6 passwordRepeat-Empty" +
            " #7 email-Null" +
            " #8 email-Empty")
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
        //Проверка
        receivedResponse.assertThat().statusCode(422);
    }
        // метод с тестируемыми данными (аргументы)
        private static Stream<Arguments> provideDifferentRequestBodies () {
            GenerateData generateData = new GenerateData();
            String generatedPassword = generateData.getBase64EncodedPsw();

            PlayerModelToSignUp bodyWithNullUsername = PlayerModelBuilder
                    .defaultValues()
                    .setUsername(null)
                    .setPasswordChange(generatedPassword)
                    .setPasswordRepeat(generatedPassword)
                    .setEmail(generateData.getRandomEmail())
                    .build();
            PlayerModelToSignUp bodyWithEmptyUsername = PlayerModelBuilder
                    .defaultValues()
                    .setUsername("")
                    .setPasswordChange(generatedPassword)
                    .setPasswordRepeat(generatedPassword)
                    .setEmail(generateData.getRandomEmail())
                    .build();
            PlayerModelToSignUp bodyWithNullPasswordChange = PlayerModelBuilder
                    .defaultValues()
                    .setUsername(generateData.getRandomUserName())
                    .setPasswordChange(null)
                    .setPasswordRepeat(generatedPassword)
                    .setEmail(generateData.getRandomEmail())
                    .build();
            PlayerModelToSignUp bodyWithEmptyPasswordChange = PlayerModelBuilder
                    .defaultValues()
                    .setUsername(generateData.getRandomUserName())
                    .setPasswordChange("")
                    .setPasswordRepeat(generatedPassword)
                    .setEmail(generateData.getRandomEmail())
                    .build();
            PlayerModelToSignUp bodyWithNullPasswordRepeat = PlayerModelBuilder
                    .defaultValues()
                    .setUsername(generateData.getRandomUserName())
                    .setPasswordChange(generatedPassword)
                    .setPasswordRepeat(null)
                    .setEmail(generateData.getRandomEmail())
                    .build();
            PlayerModelToSignUp bodyWithEmptyPasswordRepeat = PlayerModelBuilder
                    .defaultValues()
                    .setUsername(generateData.getRandomUserName())
                    .setPasswordChange(generatedPassword)
                    .setPasswordRepeat("")
                    .setEmail(generateData.getRandomEmail())
                    .build();
            PlayerModelToSignUp bodyWithNullEmail = PlayerModelBuilder
                    .defaultValues()
                    .setUsername(generateData.getRandomUserName())
                    .setPasswordChange(generatedPassword)
                    .setPasswordRepeat(generatedPassword)
                    .setEmail(null)
                    .build();
            PlayerModelToSignUp bodyWithEmptyEmail = PlayerModelBuilder
                    .defaultValues()
                    .setUsername(generateData.getRandomUserName())
                    .setPasswordChange(generatedPassword)
                    .setPasswordRepeat(generatedPassword)
                    .setEmail("")
                    .build();

            return Stream.of(
                    Arguments.of(bodyWithNullUsername),
                    Arguments.of(bodyWithEmptyUsername),
                    Arguments.of(bodyWithNullPasswordChange),
                    Arguments.of(bodyWithEmptyPasswordChange),
                    Arguments.of(bodyWithNullPasswordRepeat),
                    Arguments.of(bodyWithEmptyPasswordRepeat),
                    Arguments.of(bodyWithNullEmail),
                    Arguments.of(bodyWithEmptyEmail)
            );
        }
    }
