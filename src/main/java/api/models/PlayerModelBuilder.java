package api.models;

public class PlayerModelBuilder {
    private PlayerModelToSignUp playerModelToSignUp;

    public PlayerModelBuilder() {
        playerModelToSignUp = new PlayerModelToSignUp();
    }

    public static PlayerModelBuilder defaultValues () {
        return new PlayerModelBuilder();
    }

    public PlayerModelBuilder setUsername(String username) {
        playerModelToSignUp.setUsername(username);
        return this;
    }

    public PlayerModelBuilder setPasswordChange(String passwordChange) {
        playerModelToSignUp.setPasswordChange(passwordChange);
        return this;
    }

    public PlayerModelBuilder setPasswordRepeat(String passwordRepeat) {
        playerModelToSignUp.setPasswordRepeat(passwordRepeat);
        return this;
    }

    public PlayerModelBuilder setEmail(String email) {
        playerModelToSignUp.setEmail(email);
        return this;
    }

    public PlayerModelBuilder setName(String name) {
        playerModelToSignUp.setName(name);
        return this;
    }

    public PlayerModelBuilder setSurname(String surname) {
        playerModelToSignUp.setSurname(surname);
        return this;
    }

    public PlayerModelBuilder setCurrency_code(String currency_code) {
        playerModelToSignUp.setCurrency_code(currency_code);
        return this;
    }

    public PlayerModelToSignUp build () {
        return playerModelToSignUp;
    }

}


