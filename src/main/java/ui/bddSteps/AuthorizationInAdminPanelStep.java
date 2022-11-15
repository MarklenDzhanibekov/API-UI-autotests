package ui.bddSteps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pageObjectModels.AdminPanelPage;
import ui.pageObjectModels.LoginPage;
import ui.testData.TestData;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AuthorizationInAdminPanelStep {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    LoginPage loginPage = new LoginPage(driver);
    TestData testData = new TestData();
    AdminPanelPage adminPanelPage = new AdminPanelPage(driver, wait);

    @Given("open website {string}")
    public void openWebSite(String websiteUrl) {
        driver.get(websiteUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @When("I enter an administrator's login to the login field")
    public void fillingUpLoginField() {
        loginPage.fillInLoginField(testData.login);
    }

    @And("I enter an administrator's password to the password field")
    public void fillingUpPasswordField() {
        loginPage.fillPasswordField(testData.password);
    }

    @And("I click on the SignIn button")
    public void clickSignInButton() {
        loginPage.clickOnSignInButton();
    }

    @Then("System authorizes me as an Administrator")
    public void systemAuthorizesMeAsAnAdministrator() {
        adminPanelPage.displayedLoginNameInDashboard();
        String displayedLoginNameInDashboard = adminPanelPage.displayedLoginNameInDashboard();
        assertTrue(displayedLoginNameInDashboard.contains(testData.login));
    }

    @And("the administrator panel is successfully loaded")
    public void theAdministratorPanelIsSuccessfullyLoaded() {
        adminPanelPage.sidebarIsDisplayed();
    }

    @Given("I am logged in the admin panel as an Administrator")
    public void iAmLoggedInTheAdminPanelAsAnAdministrator() {
        loginPage.authorizeInAdminPanel(testData.login, testData.password);
    }

    @When("I click on the User tab the Players tab is shown")
    public void iClickOnTheUserTabThePlayersTabIsShown() {
        adminPanelPage.openUsersTabSideBarClick();
    }

    @And("Click on the Players tab open a table with players")
    public void clickOnThePlayersTabOpenATableWithPlayers() {
        adminPanelPage.openPlayersSubTabSideBar();
    }

    @Then("The table with players is successfully shown")
    public void theTableWithPlayersIsSuccessfullyShown() {
        adminPanelPage.playersTableIsDisplayed();
        assertTrue(adminPanelPage.playersTableIsDisplayed());
    }

    @Given("I am logged in the admin panel as an Administrator and the table with players is opened")
    public void theTableWithPlayersIsOpenedAndShownToMe() {
        loginPage.authorizeInAdminPanel(testData.login, testData.password);
        adminPanelPage.openUsersTabSideBarClick();
        adminPanelPage.openPlayersSubTabSideBar();
        adminPanelPage.playersTableIsDisplayed();
    }

    @When("Clicking on a name of the External ID column initiates sorting by Ascending order of IDs number")
    public void clickingOnANameOfTheExternalIDColumnInitiatesSortingByAscendingOrderOfIDsNumber() {
        adminPanelPage.sortByAscending();
    }

    @And("Wait for the sorting is done")
    public void waitForTheSortingIsDone() {
        //ждем пока сортировка вступит в силу
        adminPanelPage.waitForSortingDone();
    }

    @Then("At least the first twenty External ID numbers should be correctly sorted by Ascending")
    public void atLeastTheFirstTwentyExternalIDNumbersShouldBeCorrectlySortedByAscending() {
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
    }

    @After
    public void afterScenario() {
        driver.quit();
    }
}