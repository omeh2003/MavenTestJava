import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.lang.reflect.Field;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

class GooglePage {

    private static WebDriver driver = GetMyDrive();

    private static WebDriver GetMyDrive() {
        return GoogleTest.MyDrive;
    }

    static void GoToSearchPage() {

        driver.navigate().to("http://www.google.com/");
        WaitForTitle("Google");
        if (GoogleTest.Promo) {
            GoogleTest.Promo = false;
            WaitForElement(By.linkText("×"), 5, false);
        }
        if (CheckSelector(By.linkText("×"))) {
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

        CheckSelector(by);
    }


    static void WaitForElement(By by) {
        WaitForElement(by, 10);
    }

    static void WaitForElement(By by, int seconds) {
        WaitForElement(by, seconds, true);
    }

    static void WaitForElement(By by, int seconds, boolean fails) {
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            if (fails) {
                fail("WebDriverWait не дождался елемента: " + by.toString() + " " + "Ошибка: " + e.getMessage());
            } else {
                Reporter.log("WebDriverWait не дождался елемента: " + by.toString() + " " + "Ошибка: " + e.getMessage());
            }
        }
    }

    static void WaitForTitle(String title) {
        WaitForTitle(title, 10);
    }

    static void WaitForTitle(String title, int seconds) {
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.titleIs(title));
        } catch (Exception e) {
            fail("WebDriverWait не дождался Title: " + title + " " + "Ошибка: " + e.getLocalizedMessage());
        }
    }


    static boolean CheckSelector(By selector) {
        try {
            Reporter.log("Проверка селектора: " + selector.toString(), true);
            //ReportObject(selector);
            WebElement w = driver.findElement(selector);
            Reporter.log("Проверка селектора WebElement: " + w.toString(), true);
            //ReportObject(w);
            Reporter.log("=======================", true);
            return w.isEnabled();

        } catch (Exception e) {

            Reporter.log("Селектор ненайден. " + e.getMessage(), true);
            Reporter.log("селектор: " + selector.toString(), true);
            Reporter.log("=======================", true);
        }


        return false;
    }

    private static void ReportObject(Object obj) {
        for (Field x : obj.getClass().getDeclaredFields()
                ) {
            Reporter.log("Field name: " + x.getName(), true);
            for (Field y :
                    x.getClass().getDeclaredFields()) {
                Reporter.log("Field value: " + y.toString(), true);
            }

        }
    }

    static boolean CheckSelector(WebElement selector) {

        try {


            Reporter.log("Проверка селектора toString: " + selector.toString(), true);
            Reporter.log("Проверка селектора getTagName: " + selector.getTagName(), true);
            Reporter.log("Проверка селектора getText: " + selector.getText(), true);
            Reporter.log("isEnabled: " + selector.isEnabled(), true);

            Reporter.log("=======================", true);

            return selector.isEnabled();

        } catch (Exception e) {

            Reporter.log("Селектор ненайден. " + e.getMessage(), true);
            Reporter.log("селектор: " + selector.toString(), true);
            Reporter.log("=======================", true);
        }


        return false;

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
