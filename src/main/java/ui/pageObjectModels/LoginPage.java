package ui.pageObjectModels;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    By casinoWord = By.className("ng-binding");
    By loginField = By.cssSelector("[placeholder=\"Login\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By signInButton = By.cssSelector("[value=\"Sign in\"]");

    public void isVisibleCasino() {
        driver.findElement(casinoWord).isDisplayed();
    }

    public void fillInLoginField(String login) {
        driver.findElement(loginField).isDisplayed();
        driver.findElement(loginField).click();
        driver.findElement(loginField).sendKeys(login);
    }

    public void fillPasswordField(String password) {
        driver.findElement(passwordField).isDisplayed();
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickOnSignInButton() {
        driver.findElement(signInButton).isDisplayed();
        driver.findElement(signInButton).click();
    }

    public void authorizeInAdminPanel(String login, String password) {
        this.isVisibleCasino();
        this.fillInLoginField(login);
        this.fillPasswordField(password);
        this.clickOnSignInButton();
    }
}
