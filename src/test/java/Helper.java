import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.lang.reflect.Field;

import static org.testng.Assert.fail;

/**
 * Created by Ivan on 10.05.2016.
 */
public class Helper {

    static void WaitForElement(By by) {
        WaitForElement(by, 10);
    }

    static void WaitForElement(By by, int seconds) {
        WaitForElement(by, seconds, true);
    }

    static void WaitForElement(By by, int seconds, boolean stopfail) {
        try {
            new WebDriverWait(GoogleTest.MyDrive, seconds).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            if (stopfail) {
                fail("WebDriverWait не дождался елемента: " + by.toString() + " " + "Ошибка: " + e.getMessage());
            } else {
                Reporter.log("WebDriverWait не дождался элемента: " + by.toString() + " " + "Ошибка: " + e.getMessage());
                Reporter.log("Продолжаю работу.");
                Reporter.log("===========================================");
            }
        }
    }

    static void WaitForTitle(String title) {
        WaitForTitle(title, 10);
    }

    static void WaitForTitle(String title, int seconds) {
        try {
            new WebDriverWait(GoogleTest.MyDrive, seconds).until(ExpectedConditions.titleIs(title));
        } catch (Exception e) {
            fail("WebDriverWait не дождался Title: " + title + " " + "Ошибка: " + e.getLocalizedMessage());
        }
    }

    static boolean CheckSelector(By selector) {

        try {

            WebElement webElement = GoogleTest.MyDrive.findElement(selector);
            Reporter.log("Проверка селектора toString: " + webElement.toString(), true);
            Reporter.log("Проверка селектора getTagName: " + webElement.getTagName(), true);
            Reporter.log("Проверка селектора getText: " + webElement.getText(), true);
            Reporter.log("isEnabled: " + webElement.isEnabled(), true);

            Reporter.log("=======================", true);

            return webElement.isEnabled();

        } catch (Exception e) {

            Reporter.log("Селектор ненайден. " + e.getMessage(), true);
            Reporter.log("селектор: " + selector.toString(), true);
            Reporter.log("=======================", true);
        }


        return false;


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

    static void WaitAndCheckSelector(By by, int seconds, boolean stopfail) {
        if (!CheckSelector(by)) WaitForElement(by, seconds, stopfail);
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
}
