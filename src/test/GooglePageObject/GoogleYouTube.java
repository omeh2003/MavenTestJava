import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created  on 08.05.2016.
 */
public class GoogleYouTube {

    // Поле поиска на "YouTube"
    public static GoogleButton ButtonSearchYouTube() {
        return new GoogleButton(GoogleButton.Selector.ID, "masthead-search-term", "Поле поиска \"YouTube\"");
    }

    // Кнопка поиска на "YouTube"
    public static GoogleButton ButtonSearchYouTubeOK() {
        return new GoogleButton(GoogleButton.Selector.ID, "search-btn", "Кнопка поиска \"YouTube\"");
    }

    public static List<WebElement> GetResult() {
        return GoogleTest.MyDrive.findElements(By.className("yt-lockup-title"));

    }

}
