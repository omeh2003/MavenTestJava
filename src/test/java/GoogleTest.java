import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class GoogleTest {

    static WebDriver MyDrive;
    static boolean Promo;


    @BeforeTest
    public void setUp() {

        Promo = true;
        MyDrive = new FirefoxDriver();
        MyDrive.manage().window().maximize();

    }

    @AfterTest
    public void tearDown() {

        if (!Promo) Promo = true;
        MyDrive.quit();

    }

    @Test
    // Проверка работы поиска
    public void userCanSearch() {
        GooglePage.GoToSearchPage();
        GooglePage.Search("Selenide", "Selenide");
    }

    @Test(dataProvider = "searchData")
    // Проверка работы поиска с набором входных данных
    public void userSearch(String searcher, String result) {
        GooglePage.GoToSearchPage();
        GooglePage.Search(searcher, result);
    }

    @Test
    // Проверка наличия кнопки "Поиск в Google" на главной странице
    public void checkButtonSearchInGoogle() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckGoogleButton(GoogleButton.SearchInGoogle());
    }

    @Test
    // Проверка наличия кнопки "Мне повезёт!" на главной странице
    public void checkButtonFeelingLucky() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckGoogleButton(GoogleButton.FeelingLucky());
    }

    @Test
    // Проверка наличия кнопки "Войти" на главной странице
    public void checkButtonLoginIn() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckGoogleButton(GoogleButton.LoginIn());
    }

    @Test
    // Проверка наличия кнопки "Почта" на главной странице
    public void checkButtonMail() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckGoogleButton(GoogleButton.Mail());
    }

    @Test
    // Проверка доступности GooglePlay с Главной страницы
    public void checkGooglePlay() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckGoogleButton(GoogleButton.ButtonProgramm());
        GooglePage.GetWebElement(GoogleButton.ButtonProgramm()).click();
        GooglePage.CheckGoogleButton(GoogleButton.ButtonGooglePlay());
        GooglePage.GetWebElement(GoogleButton.ButtonGooglePlay()).click();
        GooglePage.WaitForElement(By.cssSelector("span.gb_Rb"));
        assertTrue(MyDrive.getTitle().equals("Google Play"));


    }

    @Test
    //Проверка Переводчика
    public void checkGoogleTranslate() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckGoogleButton(GoogleButton.ButtonProgramm());
        GooglePage.GetWebElement(GoogleButton.ButtonProgramm()).click();
        GooglePage.CheckGoogleButton(GoogleButton.ButtonTranslate());
        GooglePage.GetWebElement(GoogleButton.ButtonTranslate()).click();
        GooglePage.WaitForElement(By.id("gbq1"));
        assertTrue(MyDrive.getTitle().equals("Google Переводчик"));
        GooglePage.CheckGoogleButton(GoogleTranslete.SourceTexBox());
        GooglePage.GetWebElement(GoogleTranslete.SourceTexBox()).sendKeys("Hi Google");
        GooglePage.CheckGoogleButton(GoogleTranslete.ButtonTranslete());
        GooglePage.GetWebElement(GoogleTranslete.ButtonTranslete()).click();
        GooglePage.CheckGoogleButton(GoogleTranslete.ResultTexBox());
        int count = 0;
        while (GooglePage.GetWebElement(GoogleTranslete.ResultTexBox()).getText().isEmpty()) {
            Reporter.log("Ждем перевода ", true);
            Reporter.log("Count: " + count, true);
            Reporter.log(GooglePage.GetWebElement(GoogleTranslete.ResultTexBox()).getText(), true);
            Reporter.log("=======================", true);
            count++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        WebElement result = GooglePage.GetWebElement(GoogleTranslete.ResultTexBox());
        GooglePage.CheckSelector(result);

        assert result != null;
        assertTrue(result.getText().startsWith("Привет Google"));

    }

    @Test
    //Проверка Youtube
    public void checkYoutube() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckGoogleButton(GoogleButton.ButtonProgramm());
        GooglePage.GetWebElement(GoogleButton.ButtonProgramm()).click();
        GooglePage.CheckGoogleButton(GoogleButton.ButtonYouTube());
        GooglePage.GetWebElement(GoogleButton.ButtonYouTube()).click();
        GooglePage.WaitForElement(By.id("logo-container"));
        assertTrue(MyDrive.getTitle().equals("YouTube"));
        GooglePage.CheckGoogleButton(GoogleYouTube.ButtonSearchYouTube());
        GooglePage.GetWebElement(GoogleYouTube.ButtonSearchYouTube()).sendKeys("Смешные котики");
        GooglePage.CheckGoogleButton(GoogleYouTube.ButtonSearchYouTubeOK());
        GooglePage.GetWebElement(GoogleYouTube.ButtonSearchYouTubeOK()).click();
        GooglePage.WaitForElement(By.className("yt-lockup-title"));
        GooglePage.CheckSelector(By.className("yt-lockup-title"));
        String result = GoogleYouTube.GetResult().get(0).getText();
        Reporter.log("Первый результат: " + result, true);
        Reporter.log("=======================", true);
        assert result != null;
        assertTrue(!result.isEmpty());

    }

    @DataProvider(name = "searchData")
    public Object[][] searchData() {
        return new Object[][]{
                {"Смешные котики", "Смешные котики"},
                {"Есть ли жизнь на Марсе", "Есть ли жизнь на марсе"},
                {"Бочка рома", "Бочка рома"},
                {"GTA5", "GTA5"}
        };
    }
}
