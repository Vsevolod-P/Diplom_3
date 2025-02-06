package Tests;

import HelperApi.User;
import HelperApi.UserApi;
import HelperApi.UserCreationApi;
import HelperClasses.LoginPage;
import HelperClasses.MainPage;
import HelperClasses.WebDriverFactory;
import HelperClasses.PersonalCabinetPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static HelperApi.URL.BASE_URL_LOGIN;

public class TestPersonalCabinet {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private PersonalCabinetPage personalCabinetPage;
    private User user;
    private HelperApi.UserApi UserApi;
    private boolean isUserCreated;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.get(BASE_URL_LOGIN);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        personalCabinetPage = new PersonalCabinetPage(driver);
        UserApi = new UserApi();
        user = UserCreationApi.createUser();
        isUserCreated = false;
    }
    @After
    public void tearDown() {
        driver.quit();
        if (isUserCreated) {
            UserApi.deleteUser(user.getEmail(), user.getPassword());
        }
    }

    @Test
    @Description("Проверка выхода из личного кабинета")
    @Step("Входим и выходим из кабинета")
    public void testLogin() {
        UserApi.createUserRequest(user);
        loginPage.loginData(user.getEmail(), user.getPassword());
        loginPage.loginClick();
        mainPage.goToPersonalCabinet();
        WebDriverWait waitLoad = new WebDriverWait(driver, Duration.ofSeconds(3));
        waitLoad.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Выход']")));
        personalCabinetPage.logout();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Вход')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");
        isUserCreated = true;
    }

    @Test
    @Description("Проверка перехода из кабинета на Конструктор")
    @Step("Переходим из личного кабинета в конструтор")
    public void testGoToConstructor() {
        mainPage.goToPersonalCabinet();
        personalCabinetPage.goToConstructor();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Соберите бургер')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Description("Проверка перехода из кабинета на Конструктор")
    @Step("Переходим из личного кабинета в конструтор")
    public void testGoToLogo() {
        mainPage.goToPersonalCabinet();
        personalCabinetPage.goToLogo();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Соберите бургер')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
    }
}
