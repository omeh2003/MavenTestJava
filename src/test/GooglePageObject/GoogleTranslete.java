/**
 * Created  on 08.05.2016.
 */
public class GoogleTranslete {

    // TextBox для ввода текста в переводчик
    public static GoogleButton SourceTexBox() {
        return new GoogleButton(GoogleButton.Selector.ID, "source", "TextBox для ввода текста в переводчик");
    }

    //TextBox c результатом перевода
    public static GoogleButton ResultTexBox() {
        return new GoogleButton(GoogleButton.Selector.ID, "result_box", "TextBox c результатом перевода");
    }

    //Button перевести
    public static GoogleButton ButtonTranslete() {
        return new GoogleButton(GoogleButton.Selector.ID, "gt-submit", "Button перевести");
    }
}
