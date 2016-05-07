import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class GooglePage {


    public static void GoToSearchPage() {

        GoogleTest.MyDrive.navigate().to("http://www.google.com/");
        //Ожидание загрузки страницы
        try {
            new WebDriverWait(GoogleTest.MyDrive, 4).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#hplogo")));
        } catch (Exception e) {

            fail("WebDriverWait не дождался загрузки страницы http://www.google.ru/ " + "Ошибка: " + e.getMessage());
        }

    }

    public static void Search(String searchLine, String expectedResult) {
        GooglePage p = PageFactory.initElements(GoogleTest.MyDrive, GooglePage.class);
        SearchResultsPage results = p.SearchFor(searchLine);
        assertTrue(results.getResults().get(0).getText().startsWith(expectedResult));
    }

    public static void CheckElement(GoogleButton button) {
        By by = null;
        switch (GoogleButton.Selector) {
            case CSS:
                by = By.cssSelector(button.Name);
                break;
            case ID:
                by = By.id(button.Name);
                break;
            case XPATH:
                by = By.xpath(button.Name);
                break;
            case NAME:
                by = By.name(button.Name);
                break;
            case CLASS:
                by = By.className(button.Name);
                break;
        }

        try {


            new WebDriverWait(GoogleTest.MyDrive, 5).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {

            fail("WebDriverWait не дождался кнопки " + button.ButtonName + " Ошибка: " + e.getMessage());
        }


        assertTrue(GoogleTest.MyDrive.findElement(by).isEnabled());
    }

    public SearchResultsPage SearchFor(String text) {
        GoogleButton.StringSearch().sendKeys(text);
        GoogleButton.ButtonOk().click();
        // Ожидание результатов поиска
        try {
            new WebDriverWait(GoogleTest.MyDrive, 4).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ires .g")));
        } catch (Exception e) {
            fail("WebDriverWait не дождался загрузки  результатов поиска. Запрос: " + text + " " + "Ошибка: " + e.getMessage());
        }

        return PageFactory.initElements(GoogleTest.MyDrive, SearchResultsPage.class);
    }


}
