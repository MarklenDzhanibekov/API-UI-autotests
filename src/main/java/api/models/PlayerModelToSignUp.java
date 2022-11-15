package api.models;

public class PlayerModelToSignUp {
    private String username;
    private String password_change;
    private String password_repeat;
    private String email;
    private String name;
    private String surname;
    private String currency_code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordChange() {
        return password_change;
    }

    public void setPasswordChange(String password_change
    ) {
        this.password_change = password_change;
    }

    public String getPasswordRepeat() {
        return password_repeat;
    }

    public void setPasswordRepeat(String password_repeat) {
        this.password_repeat = password_repeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
}
