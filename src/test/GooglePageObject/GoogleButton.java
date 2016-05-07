import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created  on 07.05.2016.
 */
public class GoogleButton {
    public static GoogleButton.Selector Selector;
    public String Name;
    public String ButtonName;

    public GoogleButton(GoogleButton.Selector selector, String name, String buttonName) {
        this.Name = name;
        Selector = selector;
        this.ButtonName = buttonName;
    }

    // Кнопка "Поиск в Google"
    public static GoogleButton SearchInGoogle() {
        return new GoogleButton(GoogleButton.Selector.CSS, "input[name=\"btnK\"]", "Поиск в Google");
    }

    // Кнопка "Мне повезёт!"
    public static GoogleButton FeelingLucky() {
        return new GoogleButton(GoogleButton.Selector.CSS, "input[name=\"btnI\"]", "Мне повезёт!");
    }

    // Кнопка "Войти!"
    public static GoogleButton LoginIn() {
        return new GoogleButton(GoogleButton.Selector.ID, "gb_70", "Войти!");
    }

    // Кнопка "Почта"
    public static GoogleButton Mail() {
        return new GoogleButton(GoogleButton.Selector.CLASS, "gb_P", "Почта");
    }

    // Поле ввода поискового запроса
    public static WebElement StringSearch() {
        return GoogleTest.MyDrive.findElement(By.id("lst-ib"));
    }

    // Кнопка "Поиск"
    public static WebElement ButtonOk() {

        return GoogleTest.MyDrive.findElement(By.id("sblsbb"));
    }

    // Кнопка "GooglePlay"
    public static WebElement ButtonGooglePlay() {

        return GoogleTest.MyDrive.findElement(By.cssSelector("#gb78 > span.gb_3"));
    }

    // Кнопка "Приложения"
    public static WebElement ButtonProgramm() {

        return GoogleTest.MyDrive.findElement(By.cssSelector("a.gb_b.gb_Qb"));
    }


    public enum Selector {CSS, ID, XPATH, NAME, CLASS}
}

