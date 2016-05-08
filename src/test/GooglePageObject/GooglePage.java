import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

class GooglePage {


    static void GoToSearchPage() {

        GoogleTest.MyDrive.navigate().to("http://www.google.com/");
        WaitForElement(By.cssSelector("#hplogo"));
        if (GoogleTest.Promo) {
            GoogleTest.Promo = false;
            if (!CheckSelector(By.linkText("×"))) {
                try {
                    Reporter.log("Жду елемента PROMO на главной ", true);
                    Reporter.log("=======================", true);
                    new WebDriverWait(GoogleTest.MyDrive, 5).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("×")));
                } catch (Exception e) {
                    Reporter.log("WebDriverWait не дождался елемента PROMO: " + "Ошибка: " + e.getMessage(), true);
                    Reporter.log("=======================", true);
                }
            }
            if (CheckSelector(By.linkText("×"))) {
                Reporter.log("Кликаю елемент PROMO на главной ", true);
                GoogleTest.MyDrive.findElement(By.linkText("×")).click();
                Reporter.log("=======================", true);
            }
        }
    }

    static void Search(String searchLine, String expectedResult) {

        assertTrue(PageFactory.initElements(GoogleTest.MyDrive, SearchResultsPage.class).SearchFor(searchLine).getResults().get(0).getText().startsWith(expectedResult));
    }

    static void CheckElement(GoogleButton button) {
        By by = null;
        switch (button.SelectorElemet) {
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

        CheckSelector(by);
    }


    static void WaitForElement(By by) {
        try {
            new WebDriverWait(GoogleTest.MyDrive, 10).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            fail("WebDriverWait не дождался елемента: " + by.toString() + " " + "Ошибка: " + e.getMessage());
        }


    }

    static void WaitForElement(WebElement by) {
        try {
            new WebDriverWait(GoogleTest.MyDrive, 10).until(ExpectedConditions.visibilityOf(by));
        } catch (Exception e) {
            fail("WebDriverWait не дождался елемента: " + by.toString() + " " + "Ошибка: " + e.getMessage());
        }


    }

    public static boolean CheckSelector(By selector) {
        try {

            String atribute = "";

            Reporter.log("Проверка селектора By: " + selector.toString(), true);
            WebElement w = GoogleTest.MyDrive.findElement(selector);
            Reporter.log("Проверка селектора WebElement: " + w.getText(), true);
            Reporter.log("isEnabled: " + w.isEnabled(), true);
            Reporter.log("=======================", true);

            return true;

        } catch (Exception e) {

            Reporter.log("Селектор ненайден. " + e.getMessage(), true);
            Reporter.log("селектор: " + selector.toString(), true);
            Reporter.log("=======================", true);
        }


        return false;
    }

    public static boolean CheckSelector(WebElement selector) {

        try {

            String atribute = "";

            Reporter.log("Проверка селектора toString: " + selector.toString(), true);
            Reporter.log("Проверка селектора getTagName: " + selector.getTagName(), true);
            Reporter.log("Проверка селектора getText: " + selector.getText(), true);
            Reporter.log("isEnabled: " + selector.isEnabled(), true);
            Reporter.log("getAttribute: " + selector.getAttribute(atribute), true);
            Reporter.log("getCssValue: " + selector.getCssValue(atribute), true);
            Reporter.log("=======================", true);

            return (selector != null && selector.isEnabled());

        } catch (Exception e) {

            Reporter.log("Селектор ненайден. " + e.getMessage(), true);
            Reporter.log("селектор: " + selector.toString(), true);
            Reporter.log("=======================", true);
        }


        return false;

    }

    public static WebElement GetWebElement(GoogleButton button) {
        By by = null;
        switch (button.SelectorElemet) {
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
            Reporter.log("SelectorElemet: " + button.SelectorElemet, true);

            WebElement webElement = GoogleTest.MyDrive.findElement(by);
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
