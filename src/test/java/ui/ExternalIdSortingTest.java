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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExternalIdSortingTest {
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

    @DisplayName("тест сортировки по возрастанию и убыванию столбца 'External ID'")
    @Test
    public void externalIdColumnIsSoringTest() {
        loginPage.authorizeInAdminPanel(testData.login, testData.password);
        //авторизация в панель админа произошла успешно
        adminPanelPage.displayedLoginNameInDashboard();
        //открываем вкладку Users
        adminPanelPage.openUsersTabSideBarClick();
        //открываем вкладку Players
        adminPanelPage.openPlayersSubTabSideBar();
        //Сортируем по ВОЗРАСТАНИЮ столбец external ID
        adminPanelPage.sortByAscending();
        //ждем пока сортировка вступит в силу
        adminPanelPage.waitForSortingDone();
        //формируем список из отсортированных по возрастанию external ID номеров
        List<String> ascendingExternalIdNumbers = adminPanelPage.firstCollectionExternalIdFromTable();
        //выбираем кол-во отображаемых игроков в таблице
        adminPanelPage.select100PlayersToBeDisplayed();
        //переходим на последнюю страницу списка игроков
        adminPanelPage.clickOnLastPageButton();
        //Сортируем по УБЫВАНИЮ столбец external ID
        adminPanelPage.sortingByDescending();
        //формирование второго списка
        List<String> descendingExternalIdNumbers = adminPanelPage.secondCollectionExternalIdFromTable();
        //Проверка сравнение обоих списков.
        assertTrue(ascendingExternalIdNumbers.equals(descendingExternalIdNumbers), "списки не равны, сортировка не верна");
        driver.quit();
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}
