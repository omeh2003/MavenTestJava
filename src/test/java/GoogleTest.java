
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class GoogleTest {
    static WebDriver MyDrive;


    @BeforeTest
  public void setUp() {



          MyDrive = new FirefoxDriver();
          MyDrive.manage().window().maximize();

  }

  @AfterTest
  public void tearDown()  {


          MyDrive.quit();

      }


  @Test
  public void userCanSearch() {
    MyDrive.navigate().to("http://www.google.com/");
    GooglePage page = PageFactory.initElements(MyDrive, GooglePage.class);
    SearchResultsPage results = page.searchFor("Selenide");
    assertTrue(results.getResults().get(0).getText().startsWith("Selenide: concise UI tests in Java"));
  }
}
