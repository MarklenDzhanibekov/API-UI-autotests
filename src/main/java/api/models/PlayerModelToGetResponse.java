package api.models;

public class PlayerModelToGetResponse {
    private Long id;
    private Integer country_id;
    private Integer timezone_id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String gender;
    private Integer phone_number;
    private Integer birthdate;
    private Boolean bonuses_allowed;
    private Boolean is_verified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public Integer getTimezone_id() {
        return timezone_id;
    }

    public void setTimezone_id(Integer timezone_id) {
        this.timezone_id = timezone_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Integer birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getBonuses_allowed() {
        return bonuses_allowed;
    }

    public void setBonuses_allowed(Boolean bonuses_allowed) {
        this.bonuses_allowed = bonuses_allowed;
    }

    public Boolean getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Boolean is_verified) {
        this.is_verified = is_verified;
    }
}
