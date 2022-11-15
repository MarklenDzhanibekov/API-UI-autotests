package ui.pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminPanelPage {
    WebDriver driver;
    WebDriverWait wait;

    public AdminPanelPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    By loginNameInDashboard = By.cssSelector("[class=\"dropdown text-normal nav-profile\"]");
    By usersSubTabSideBar = By.cssSelector("[data-target=\"#s-menu-users\"]");
    By playersSubTabSideBar = By.xpath("//a[text()='Players']");
    By playersGeneralTable = By.cssSelector("[class=\"panel-body\"]");
    By playersGeneralTableBody = By.xpath("//tbody");
    By sortByExternalIdButton = By.xpath("//*[@class='sort-link' and text()='External ID']");
    By firstExternalIdBeforeSorting = By.xpath("//tr[1]/td[3]");
    By externalIdsInTable = By.xpath("//tr/td[3]");
    By pageSizePlayers = By.id("pageSizePlayers");
    By lastPageButton = By.xpath("//*[text()='Last >>']");
    By sortByAscBtn = By.xpath("//*[@class='sort-link asc' and text()='External ID']");
    By sideBar = By.xpath("//ul[@class=\"main-side-menu\"]");

    public void sidebarIsDisplayed () {
        driver.findElement(sideBar).isDisplayed();
    }

    public By firstExternalIdBeforeSorting() {
        return firstExternalIdBeforeSorting;
    }

    //формирование второго списка
    public List<String> secondCollectionExternalIdFromTable() {
        List<WebElement> collectedElementsAfterSorting = driver.findElements(externalIdsInTable);
        List<String> descendingExternalIdNumbers = new ArrayList<>();
        wait.until(ExpectedConditions.presenceOfElementLocated(firstExternalIdBeforeSorting));
        for (WebElement currentElement : collectedElementsAfterSorting) {
            descendingExternalIdNumbers.add(currentElement.getText());
        }

        //оставляем в списке только последнии 10 элементов, которые нам нужны будут для дальнейшего сравнения списков
        descendingExternalIdNumbers.subList(0, descendingExternalIdNumbers.size() - 10).clear();
        Collections.reverse(descendingExternalIdNumbers);
        System.out.println(descendingExternalIdNumbers);
        System.out.println(descendingExternalIdNumbers.size());
        return descendingExternalIdNumbers;
    }
//
//    By lastPageNumber = By.xpath("//li[@class=' active']");
//    public List<String> searchNumbers() {
//        List<String> finalList = new ArrayList<>();
//        List<WebElement> collectedElementsAfterSorting = driver.findElements(externalIdsInTable);
//        List<String> descendingExternalIdNumbers = new ArrayList<>();
//        wait.until(ExpectedConditions.presenceOfElementLocated(firstExternalIdBeforeSorting));
//        for (WebElement currentElement : collectedElementsAfterSorting) {
//            descendingExternalIdNumbers.add(currentElement.getText());
//        }
//        if  (descendingExternalIdNumbers.size() >= 20) {
//            Integer missingAmount = 20 - descendingExternalIdNumbers.size();
//
//            driver.findElement(lastPageNumber).getText();
//            String lastPageNumberString = driver.findElement(lastPageNumber).getText();
//            int lastPageIntNumber = Integer.parseInt(lastPageNumberString) - 1;
//            driver.findElement(By.xpath("//*[@id=\"yw0\"]/li/a[contains(text(),'" + lastPageIntNumber + "')]")).click();
//            wait.until(ExpectedConditions.visibilityOfElementLocated(lastPageButton));
//
//            List<WebElement> listFromPreviousPage = driver.findElements(externalIdsInTable);
//            List<String> listFromPreviousPageStrings = new ArrayList<>();
//            for (WebElement currentElement : listFromPreviousPage) {
//                listFromPreviousPageStrings.add(currentElement.getText());
//            }
//
//            listFromPreviousPageStrings.subList(0, listFromPreviousPageStrings.size() - missingAmount).clear();
//            System.out.println("new list is " + listFromPreviousPageStrings);
//
//            //оставляем в списке только последнии 20 элементов, которые нам нужны будут для дальнейшего сравнения списков
//            descendingExternalIdNumbers.subList(0, descendingExternalIdNumbers.size() - 20).clear();
//            List<String> newList = new ArrayList<String>(listFromPreviousPageStrings);
//            newList.addAll(descendingExternalIdNumbers);
//            Collections.reverse(newList);
//            System.out.println("final list IS - " + newList);
//            finalList = newList;
//
//        }
//            return finalList;
//    }

    //Сортируем по УБЫВАНИЮ столбец external ID
    public void sortingByDescending() {
        //Второй раз извлекаем external ID из 1й строки до сортировки
        WebElement firstExternalIdAfterSorting = wait.until(ExpectedConditions.presenceOfElementLocated(firstExternalIdBeforeSorting));
        //Сортируем по УБЫВАНИЮ столбец external ID
        WebElement sortDescByExternalIdButton = wait.until(ExpectedConditions.presenceOfElementLocated(sortByAscBtn));
        sortDescByExternalIdButton.click();
        //ждем пока сортировка вступит в силу
        wait.until(ExpectedConditions.invisibilityOfElementWithText(firstExternalIdBeforeSorting, firstExternalIdAfterSorting.getText()));
    }

    //переходим на последнюю страницу списка игроков
    public void clickOnLastPageButton() {
        WebElement lastButton = driver.findElement(lastPageButton);
        lastButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(lastPageButton));
    }

    public void waitForSortingDone() {
        //извлекаем external ID из 1й строки до сортировки
        WebElement firstExternalIdBeforeSorting = wait.until(ExpectedConditions.presenceOfElementLocated(this.getFirstExternalIdBeforeSorting()));
        //ждем пока сортировка вступит в силу
        wait.until(ExpectedConditions.invisibilityOfElementWithText(this.firstExternalIdBeforeSorting(),
                firstExternalIdBeforeSorting.getText()));
    }

    //Сортируем по ВОЗРАСТАНИЮ столбец external ID
    public void sortByAscending() {
        wait.until(ExpectedConditions.presenceOfElementLocated(externalIdsInTable));
        this.sortByAscendingClick();
    }

    public void select100PlayersToBeDisplayed() {
        //выбираем кол-во отображаемых игроков в таблице
        Select playersToBeDisplayed = new Select(driver.findElement(pageSizePlayers));
        playersToBeDisplayed.selectByVisibleText("100");
    }

    //наполняем список элементами с externalID
    public List<String> firstCollectionExternalIdFromTable() {
        List<WebElement> collectedElements = driver.findElements(externalIdsInTable);
        List<String> ascendingExternalIdNumbers = new ArrayList<>();
        for (WebElement currentElement : collectedElements) {
            ascendingExternalIdNumbers.add(currentElement.getText());
        }
        //первый элемент пустой - удаляем его из списока
        ascendingExternalIdNumbers.remove(0);

        //оставляем в списке только последнии 10 элементов, которые нам нужны будут для дальнейшего сравнения списков
        ascendingExternalIdNumbers.subList(10, ascendingExternalIdNumbers.size()).clear();
        return ascendingExternalIdNumbers;
    }

    public By getFirstExternalIdBeforeSorting() {
        return firstExternalIdBeforeSorting;
    }

    public void sortByAscendingClick() {
        driver.findElement(sortByExternalIdButton).click();
    }

    public Boolean playersTableIsDisplayed() {
        if ((driver.findElement(playersGeneralTable).isDisplayed() && (driver.findElement(playersGeneralTableBody).isDisplayed()))) {
            return true;
        } else return false;
    }

    public String displayedLoginNameInDashboard() {
        return driver.findElement(loginNameInDashboard).getText();
    }

    //открываем вкладку Users
    public void openUsersTabSideBarClick() {
        driver.findElement(usersSubTabSideBar).isDisplayed();
        driver.findElement(usersSubTabSideBar).click();
    }

    //открываем подвкладку Players
    public void openPlayersSubTabSideBar() {
        driver.findElement(playersSubTabSideBar).isDisplayed();
        driver.findElement(playersSubTabSideBar).click();
    }
}
