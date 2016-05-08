import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {
    @FindBy(css = "#ires .g")
    public List<WebElement> results;


    public SearchResultsPage() {
        results = this.getResults();

    }

    List<WebElement> getResults() {
        return results;
    }

    SearchResultsPage SearchFor(String text) {
        GooglePage.CheckElement(GoogleButton.StringSearch());
        GooglePage.GetWebElement(GoogleButton.StringSearch()).sendKeys(text);
        GooglePage.CheckElement(GoogleButton.ButtonOk());
        GooglePage.GetWebElement(GoogleButton.ButtonOk()).click();
        GooglePage.WaitForElement(By.cssSelector("#ires .g"));
        return PageFactory.initElements(GoogleTest.MyDrive, SearchResultsPage.class);
    }

}
