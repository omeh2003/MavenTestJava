
import com.sun.org.apache.xml.internal.utils.CharKey;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

class GooglePage {

    @FindBy(css = "#lst-ib")
    public WebElement q;


    private By r=By.cssSelector("#ires .g");




    SearchResultsPage searchFor(String text) {
    q.sendKeys(text);
        q.submit();
      WebDriverWait a = new WebDriverWait(GoogleTest.MyDrive,4);
      a.until(visibilityOfElementLocated(r));
      return PageFactory.initElements(GoogleTest.MyDrive, SearchResultsPage.class);
  }
}
