import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

class GooglePage {

    public static WebDriver driver = GetMyDrive();

    private static WebDriver GetMyDrive() {
        return GoogleTest.MyDrive;
    }

    static void GoToSearchPage() {

        driver.navigate().to("http://www.google.com/");
        Helper.WaitForTitle("Google");
        if (GoogleTest.Promo) {
            GoogleTest.Promo = false;
            Helper.WaitForElement(By.linkText("×"), 5, false);
        }
        if (Helper.CheckSelector(By.linkText("×"))) {
            Reporter.log("Кликаю елемент PROMO на главной ", true);
            driver.findElement(By.linkText("×")).click();
            Reporter.log("=======================", true);
        }

    }

    static void Search(String searchLine, String expectedResult) {
        String result = PageFactory.initElements(driver, SearchResultsPage.class).SearchFor(searchLine).getResults().get(0).getText();

        assertTrue(result.contains(expectedResult), "Ждали: " + expectedResult + ". Получили: " + result + ".");
    }

    static void CheckGoogleButton(GoogleButton button) {
        By by = null;
        switch (button.SelectorElement) {
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

        Helper.CheckSelector(by);
    }


    public static WebElement GetWebElement(GoogleButton button) {
        By by = null;
        switch (button.SelectorElement) {
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
            Reporter.log("ButtonName: " + button.ButtonName, true);
            Reporter.log("Name: " + button.Name, true);
            Reporter.log("SelectorElement: " + button.SelectorElement, true);

            WebElement webElement = driver.findElement(by);
            Reporter.log(button.Name + " - isEnabled: " + webElement.isEnabled(), true);
            Reporter.log(button.Name + " -  getText: " + webElement.getText(), true);
            Reporter.log("=======================", true);
            return webElement;
        } catch (Exception e) {

            Reporter.log("Exception. Button Name: " + button.Name, true);
            Reporter.log("Exception: toString: " + e.toString(), true);
            Reporter.log("Exception: getMesage: " + e.getMessage(), true);
            Reporter.log("Exception: getLocalizedMessage: " + e.getLocalizedMessage(), true);
            Reporter.log("=======================", true);


            fail();
        }


        return null;
    }
}
