import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class GoogleTest {

    static WebDriver MyDrive;
    static boolean Promo;


    @BeforeMethod
    public void setUp() {

        Promo = true;
        MyDrive = new FirefoxDriver();
        MyDrive.manage().window().maximize();

    }

    @AfterMethod
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

    @Test(dataProvider = "buttonMenu")
    // Проверка работы кнопок в меню на главной странице
    public void checkButtonInMainMenu(GoogleButton button, String title) {
        GooglePage.GoToSearchPage();
        if (GoogleButton.ButtonProgram().WaitAndCheck()) GoogleButton.ButtonProgram().Click();
        if (button.WaitAndCheck()) button.Click();
        Helper.WaitForTitle(title);
        assertTrue(MyDrive.getTitle().equals(title));
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
        if (GoogleButton.ButtonProgram().WaitAndCheck()) GoogleButton.ButtonProgram().Click();
        if (GoogleButton.ButtonGooglePlay().WaitAndCheck()) GoogleButton.ButtonGooglePlay().Click();
        Helper.WaitForTitle("Google Play");
        assertTrue(MyDrive.getTitle().equals("Google Play"));
    }

    @Test
    //Проверка Переводчика
    public void checkGoogleTranslate() {
        GooglePage.GoToTranslatePage();
        GooglePage.CheckGoogleButton(GoogleTranslete.SourceTexBox());
        GooglePage.GetWebElement(GoogleTranslete.SourceTexBox()).sendKeys("Hi Google");
        if (GoogleTranslete.ButtonTranslete().WaitAndCheck()) GoogleTranslete.ButtonTranslete().Click();
        GooglePage.CheckGoogleButton(GoogleTranslete.ResultTexBox());
        WebElement result = GooglePage.GetWebElement(GoogleTranslete.ResultTexBox());
        Helper.CheckSelector(result);
        String s = "Привет Google";
        s = s.trim();
        assertTrue(result.getText().trim().startsWith(s), "Ждали: " + s + " Получили: " + result.getText());
    }

    @Test
    //Проверка Youtube
    public void checkYoutube() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckGoogleButton(GoogleButton.ButtonProgram());
        GooglePage.GetWebElement(GoogleButton.ButtonProgram()).click();
        GooglePage.CheckGoogleButton(GoogleButton.ButtonYouTube());
        GooglePage.GetWebElement(GoogleButton.ButtonYouTube()).click();
        Helper.WaitForElement(By.id("logo-container"));
        assertTrue(MyDrive.getTitle().equals("YouTube"));
        GooglePage.CheckGoogleButton(GoogleYouTube.ButtonSearchYouTube());
        GooglePage.GetWebElement(GoogleYouTube.ButtonSearchYouTube()).sendKeys("Смешные котики");
        GooglePage.CheckGoogleButton(GoogleYouTube.ButtonSearchYouTubeOK());
        GooglePage.GetWebElement(GoogleYouTube.ButtonSearchYouTubeOK()).click();
        Helper.WaitForElement(By.className("yt-lockup-title"));
        Helper.CheckSelector(By.className("yt-lockup-title"));
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


    @DataProvider(name = "buttonMenu")
    public Object[][] buttonMenu() {
        return new Object[][]{
                {GoogleButton.ButtonMap(), "Google Карты"},
                {GoogleButton.ButtonMyAccount(), "Настройки аккаунта Google"},
                {GoogleButton.ButtonNews(), "Новости Google"},
                {GoogleButton.ButtonSearch(), "Google"}
        };
    }
}
