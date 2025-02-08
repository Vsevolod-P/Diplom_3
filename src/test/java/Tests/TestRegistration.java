package Tests;

import HelperClasses.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import HelperClasses.MainPage;
import HelperClasses.RegistrationPage;
import HelperClasses.LoginPage;
import HelperApi.UserApi;
import HelperApi.User;
import HelperApi.UserCreationApi;

import java.time.Duration;

import static HelperApi.URL.BASE_URL;

public class TestRegistration {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private User user;
    private UserApi UserApi;
    private boolean isUserCreated;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.get(BASE_URL);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
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
    @Description("Регистрация пользователя")
    @Step("Переходим к регистрации и проверяем регистрацию")
    public void testSuccessfulRegistration() {
        mainPage.goToPersonalCabinet();
        loginPage.registrationClick();
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Вход')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/login");
        isUserCreated = true;
    }

    @Test
    @Description("Регистрация пользователя с коротким паролем")
    @Step("Регистрируем пользователя и проверяем сообщение ошибки")
    public void testRegistrationWithShortPassword() {
        mainPage.goToPersonalCabinet();
        loginPage.registrationClick();
        registrationPage.register(user.getName(), user.getEmail(), "123");
        String errorMessage = registrationPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Некорректный пароль");
    }


}