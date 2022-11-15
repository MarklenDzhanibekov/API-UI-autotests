package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pageObjectModels.AdminPanelPage;
import ui.pageObjectModels.LoginPage;
import ui.testData.TestData;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationInAdminPanelTest {
    TestData testData = new TestData();
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    LoginPage loginPage = new LoginPage(driver);
    AdminPanelPage adminPanelPage = new AdminPanelPage(driver, wait);

    @BeforeEach
    public void prepareEach() {
        driver.get(testData.getBASE_URL());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
    }

    @DisplayName("авторизация на панель администратора")
    @Test
    public void authorizationInAdminPanelTest() {
        loginPage.authorizeInAdminPanel(testData.login, testData.password);
        //проверка
        String displayedLoginNameInDashboard = adminPanelPage.displayedLoginNameInDashboard();
        assertTrue(displayedLoginNameInDashboard.contains(testData.login));
        driver.quit();
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}
