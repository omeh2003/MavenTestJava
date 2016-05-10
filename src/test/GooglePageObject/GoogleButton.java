import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created  on 07.05.2016.
 */
public class GoogleButton {
    public final Selector SelectorElement;
    public final String Name;
    public final String ButtonName;

    public GoogleButton(Selector selector, String name, String buttonName) {
        this.Name = name;
        this.SelectorElement = selector;
        this.ButtonName = buttonName;
    }

    // Кнопка "Поиск в Google"
    public static GoogleButton SearchInGoogle() {
        return new GoogleButton(Selector.CSS, "input[name=\"btnK\"]", "Поиск в Google");
    }

    // Кнопка "Мне повезёт!"
    public static GoogleButton FeelingLucky() {
        return new GoogleButton(Selector.CSS, "input[name=\"btnI\"]", "Мне повезёт!");
    }

    // Кнопка "Войти!"
    public static GoogleButton LoginIn() {
        return new GoogleButton(Selector.ID, "gb_70", "Войти!");
    }

    // Кнопка "Почта"
    public static GoogleButton Mail() {
        return new GoogleButton(Selector.CLASS, "gb_P", "Почта");
    }

    // Поле ввода поискового запроса
    public static GoogleButton StringSearch() {
        return new GoogleButton(Selector.ID, "lst-ib", "Поле ввода поискового запроса");
    }

    // Кнопка "Поиск"
    public static GoogleButton ButtonOk() {
        return new GoogleButton(Selector.ID, "sblsbb", "Кнопка \"Поиск\"");
    }

    // Кнопка "GooglePlay"
    public static GoogleButton ButtonGooglePlay() {
        return new GoogleButton(Selector.ID, "gb78", "Кнопка \"GooglePlay\"");
    }

    // Кнопка "Приложения"
    public static GoogleButton ButtonProgram() {
        return new GoogleButton(Selector.ID, "gbwa", "Кнопка \"Приложения\"");
    }

    // Кнопка "Переводчик"
    public static GoogleButton ButtonTranslate() {
        return new GoogleButton(Selector.ID, "gb51", "Кнопка \"Переводчик\"");
    }

    // Кнопка "Мой аккаунт"
    public static GoogleButton ButtonMyAccount() {
        return new GoogleButton(Selector.ID, "gb192", "Кнопка \"Мой аккаунт\"");
    }

    // Кнопка "Поиск"
    public static GoogleButton ButtonSearch() {
        return new GoogleButton(Selector.ID, "gb1", "Кнопка \"Поиск\"");
    }

    // Кнопка "Карты"
    public static GoogleButton ButtonMap() {
        return new GoogleButton(Selector.ID, "gb8", "Кнопка \"Карты\"");
    }

    // Кнопка "Новости"
    public static GoogleButton ButtonNews() {
        return new GoogleButton(Selector.ID, "gb5", "Кнопка \"Новости\"");
    }

    // Кнопка "YouTube"
    public static GoogleButton ButtonYouTube() {
        return new GoogleButton(Selector.ID, "gb36", "Кнопка \"YouTube\"");
    }

    public By ToBy() {
        By by = null;
        switch (this.SelectorElement) {
            case CSS:
                by = By.cssSelector(this.Name);
                break;
            case ID:
                by = By.id(this.Name);
                break;
            case XPATH:
                by = By.xpath(this.Name);
                break;
            case NAME:
                by = By.name(this.Name);
                break;
            case CLASS:
                by = By.className(this.Name);
                break;
        }
        return by;
    }

    public WebElement ToWebElement() {
        return GoogleTest.MyDrive.findElement(this.ToBy());
    }

    public boolean WaitAndCheck() {

        if (!Helper.CheckSelector(this.ToBy())) Helper.WaitForElement(this.ToBy(), 5, false);

        return Helper.CheckSelector(this.ToBy());
    }

    public void Click() {
        this.ToWebElement().click();
    }



    public enum Selector {CSS, ID, XPATH, NAME, CLASS}


}

