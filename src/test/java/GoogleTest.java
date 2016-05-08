import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;


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

    @Test
    // Проверка работы поиска
    public void userSearch() {
        GooglePage.GoToSearchPage();
        GooglePage.Search("Windows", "Windows");
    }

    @Test
    // Проверка наличия кнопки "Поиск в Google" на главной странице
    public void checkButtonSearchInGoogle() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckElement(GoogleButton.SearchInGoogle());
    }

    @Test
    // Проверка наличия кнопки "Мне повезёт!" на главной странице
    public void checkButtonFeelingLucky() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckElement(GoogleButton.FeelingLucky());
    }

    @Test
    // Проверка наличия кнопки "Войти" на главной странице
    public void checkButtonLoginIn() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckElement(GoogleButton.LoginIn());
    }

    @Test
    // Проверка наличия кнопки "Почта" на главной странице
    public void checkButtonMail() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckElement(GoogleButton.Mail());
    }

    @Test
    // Проверка доступности GooglePlay с Главной страницы
    public void checkGooglePlay() {
        GooglePage.GoToSearchPage();
        GooglePage.CheckElement(GoogleButton.ButtonProgramm());
        GooglePage.GetWebElement(GoogleButton.ButtonProgramm()).click();
        GooglePage.CheckElement(GoogleButton.ButtonGooglePlay());
        GooglePage.GetWebElement(GoogleButton.ButtonGooglePlay()).click();
        GooglePage.WaitForElement(By.cssSelector("span.gb_Rb"));
        assertTrue(MyDrive.getTitle().equals("Google Play"));
    }
}
