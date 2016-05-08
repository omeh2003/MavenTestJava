/**
 * Created  on 07.05.2016.
 */
public class GoogleButton {
    public final Selector SelectorElemet;
    public final String Name;
    public final String ButtonName;

    public GoogleButton(Selector selector, String name, String buttonName) {
        this.Name = name;
        this.SelectorElemet = selector;
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
    public static GoogleButton ButtonProgramm() {
        return new GoogleButton(Selector.ID, "gbwa", "Кнопка \"Приложения\"");
    }


    public enum Selector {CSS, ID, XPATH, NAME, CLASS}
}

