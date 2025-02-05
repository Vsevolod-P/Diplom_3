package Tests;

import HelperClasses.WebDriverFactory;
import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.junit.Test;
import HelperClasses.MainPage;
import HelperClasses.RegistrationPage;
import HelperApi.UserApi;
import HelperApi.User;
import HelperApi.UserCreationApi;

import java.time.Duration;

public class TestRegistration {
    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private User user;
    private UserApi UserApi;
    private boolean isUserCreated;
    private final Gson gson = new Gson();
    private String browser = "chrome";

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site");

        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        UserApi = new UserApi();
        user = UserCreationApi.createUser();
        isUserCreated = false;
    }

    @Test
    @Description("Регистрация пользователя")
    @Step("Переходим к регистрации и проверяем регистрацию")
    public void testSuccessfulRegistration() {
        mainPage.goToRegistration();
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
        mainPage.goToRegistration();
        registrationPage.register(user.getName(), user.getEmail(), "123");
        String errorMessage = registrationPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Некорректный пароль");
    }

    @After
    public void tearDown() {
        driver.quit();
        if (isUserCreated) {
            UserApi.deleteUser(user.getEmail(), user.getPassword());
        }
    }
}