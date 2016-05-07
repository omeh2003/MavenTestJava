import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class GoogleTest {


    public static WebDriver MyDrive;

    @BeforeTest
    public void setUp() {


        MyDrive = new FirefoxDriver();
        MyDrive.manage().window().maximize();

    }

    @AfterTest
    public void tearDown() {


        MyDrive.quit();

    }


    @Test
    public void userCanSearch() {
        GooglePage.GoToSearchPage();
        GooglePage.Search("Selenide", "Selenide");
    }

    @Test
    public void userSearch() {

        GooglePage.GoToSearchPage();
        GooglePage.Search("Windows", "Windows");
    }

}
