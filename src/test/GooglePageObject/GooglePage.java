import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

class GooglePage {

    static void GoToSearchPage() {

        GoogleTest.MyDrive.navigate().to("http://www.google.com/");
        Helper.WaitForTitle("Google", 50);
        if (GoogleTest.Promo) {
            GoogleTest.Promo = false;
            Helper.WaitForElement(By.linkText("×"), 5, false);
        }
        if (Helper.CheckSelector(By.linkText("×"))) {
            Reporter.log("Кликаю элемент PROMO на главной ", true);
            GoogleTest.MyDrive.findElement(By.linkText("×")).click();
            Reporter.log("=======================", true);
        }

    }

    static void Search(String searchLine, String expectedResult) {
        String result = PageFactory.initElements(GoogleTest.MyDrive, SearchResultsPage.class).SearchFor(searchLine).getResults().get(0).getText();

        assertTrue(result.contains(expectedResult), "Ждали: " + expectedResult + ". Получили: " + result + ".");
    }

    static void CheckGoogleButton(GoogleButton button) {
        Helper.CheckSelector(button.ToBy());
    }


    public static WebElement GetWebElement(GoogleButton button) {
        try {
            Reporter.log("ButtonName: " + button.ButtonName, true);
            Reporter.log("Name: " + button.Name, true);
            Reporter.log("SelectorElement: " + button.SelectorElement, true);

            WebElement webElement = GoogleTest.MyDrive.findElement(button.ToBy());
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

    public static void GoToTranslatePage() {
        GoToSearchPage();
        CheckGoogleButton(GoogleButton.ButtonProgram());
        GetWebElement(GoogleButton.ButtonProgram()).click();
        CheckGoogleButton(GoogleButton.ButtonTranslate());
        GetWebElement(GoogleButton.ButtonTranslate()).click();
        Helper.WaitForTitle("Google Переводчик");
    }
}
