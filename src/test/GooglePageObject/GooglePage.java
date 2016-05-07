import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class GooglePage {

    // Поле ввода поискового запроса
    @FindBy(css = "#lst-ib")
    public WebElement StringSearch;

    // Кнопка "Поиск"
    @FindBy(xpath = "//*[@id=\"sblsbb\"]/button")
    public WebElement ButtonOk;

    public static void GoToSearchPage() {
        GoogleTest.MyDrive.navigate().to("http://www.google.com/");
        //Ожидание загрузки страницы
        new WebDriverWait(GoogleTest.MyDrive, 4).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#hplogo")));
    }

    public static void Search(String searchLine, String expectedResult) {
        GooglePage p = PageFactory.initElements(GoogleTest.MyDrive, GooglePage.class);
        SearchResultsPage results = p.searchFor(searchLine);
        assertTrue(results.getResults().get(0).getText().startsWith(expectedResult));
    }

    public SearchResultsPage searchFor(String text) {
        StringSearch.sendKeys(text);
        ButtonOk.click();
        // Ожидание результатов поиска
        new WebDriverWait(GoogleTest.MyDrive, 4).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ires .g")));

        return PageFactory.initElements(GoogleTest.MyDrive, SearchResultsPage.class);
    }
}
