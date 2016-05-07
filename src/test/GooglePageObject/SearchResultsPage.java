import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage {
    @FindBy(css = "#ires .g")
    private List<WebElement> results;

    List<WebElement> getResults() {
        return results;
    }
}
