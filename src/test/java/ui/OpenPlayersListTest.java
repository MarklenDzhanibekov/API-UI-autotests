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

public class OpenPlayersListTest {
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

    @DisplayName("таблица с игроками открывается")
    @Test
    public void openingPlayersListTest() {
        loginPage.authorizeInAdminPanel(testData.login, testData.password);
        //авторизация в панель админа произошла успешно
        adminPanelPage.displayedLoginNameInDashboard();
        //открываем вкладку Users
        adminPanelPage.openUsersTabSideBarClick();
        //открываем вкладку Players
        adminPanelPage.openPlayersSubTabSideBar();
        //проверка - таблица с игроками успешно открыта
        assertTrue(adminPanelPage.playersTableIsDisplayed());
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}
