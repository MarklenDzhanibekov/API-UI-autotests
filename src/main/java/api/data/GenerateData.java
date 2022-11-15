package api.data;

import api.models.PlayerModelBuilder;
import api.models.PlayerModelToSignUp;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Base64;
import java.util.Currency;
import java.util.Random;
import java.util.Set;

public class GenerateData {

    public final String BASE_URL = "http://test-api.d6.dev.devcaz.com";
    public String getRandomUserName() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
    public String getRandomEmail() {
        return this.getRandomUserName() + "@gmail.com";
    }
    public String getBASE_URL() {
        return BASE_URL;
    }
    public String getBase64EncodedPsw() {
        String originalInput = RandomStringUtils.randomAlphanumeric(9, 12);
        return Base64.getEncoder().encodeToString(originalInput.getBytes());
    }
    public String getRandomString() {
        return RandomStringUtils.randomAlphabetic(6, 15);
    }
    public String getRandomCurrencyCode() {
        Set<Currency> currencySet = Currency.getAvailableCurrencies();
        int size = currencySet.size();
        int item = new Random().nextInt(size);
        int i = 0;
        Object objectToReturn = new Object();
        for (Object obj : currencySet) {
            if (i == item) objectToReturn = obj;
            i++;
        }
        return objectToReturn.toString();
    }
    public PlayerModelToSignUp getBodyWithRequiredFields() {
        String randomPassword = this.getBase64EncodedPsw();

        return PlayerModelBuilder
                .defaultValues()
                .setUsername(this.getRandomUserName())
                .setPasswordChange(randomPassword)
                .setPasswordRepeat(randomPassword)
                .setEmail(this.getRandomEmail())
                .build();
    }

    public PlayerModelToSignUp getBodyWithUsersFirstNameOptionalField() {
        String randomPassword = this.getBase64EncodedPsw();

        return PlayerModelBuilder
                .defaultValues()
                .setUsername(this.getRandomUserName())
                .setPasswordChange(randomPassword)
                .setPasswordRepeat(randomPassword)
                .setEmail(this.getRandomEmail())
                .setName(this.getRandomString())
                .build();
    }

    public PlayerModelToSignUp getBodyWithUsersSurNameOptionalField() {
        String randomPassword = this.getBase64EncodedPsw();

        return PlayerModelBuilder
                .defaultValues()
                .setUsername(this.getRandomUserName())
                .setPasswordChange(randomPassword)
                .setPasswordRepeat(randomPassword)
                .setEmail(this.getRandomEmail())
                .setSurname(this.getRandomString())
                .build();
    }

    public PlayerModelToSignUp getBodyWithCurrencyCodeOptionalField() {
        String randomPassword = this.getBase64EncodedPsw();

        return PlayerModelBuilder
                .defaultValues()
                .setUsername(this.getRandomUserName())
                .setPasswordChange(randomPassword)
                .setPasswordRepeat(randomPassword)
                .setEmail(this.getRandomEmail())
                .setCurrency_code(this.getRandomCurrencyCode())
                .build();
    }
}