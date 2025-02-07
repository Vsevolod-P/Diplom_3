package Tests;

import HelperClasses.LoginPage;
import HelperClasses.MainPage;
import HelperClasses.RegistrationPage;
import HelperClasses.ForgotPasswordPage;
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
import HelperApi.UserApi;
import HelperApi.User;
import HelperApi.UserCreationApi;
import java.time.Duration;

import static HelperApi.URL.BASE_URL;

public class TestLoginFlow {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private ForgotPasswordPage forgotPasswordPage;
    private RegistrationPage registrationPage;
    private User user;
    private UserApi UserApi;
    private boolean isUserCreated;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.get(BASE_URL);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
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
    @Description("Проверка авторизации через кнопку личный кабинет")
    @Step("Переходим на страницу логина")
    public void testLoginFromCabinet() {
        UserApi.createUserRequest(user);
        mainPage.goToPersonalCabinet();
        loginPage.loginData(user.getEmail(), user.getPassword());
        loginPage.loginClick();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Соберите бургер')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
        isUserCreated = true;
    }
    @Test
    @Description("Проверка авторизации через кнопку войти в аккаунт")
    @Step("Переходим на страницу логина")
    public void testLoginAcc() {
        UserApi.createUserRequest(user);
        mainPage.goToLoginAcc();
        loginPage.loginData(user.getEmail(), user.getPassword());
        loginPage.loginClick();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Соберите бургер')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
        isUserCreated = true;
    }

    @Test
    @Description("Проверка авторизации через кнопку войти страницы регистрации")
    @Step("Переходим на страницу логина")
    public void testLoginRegistrationPage() {
        UserApi.createUserRequest(user);
        mainPage.goToPersonalCabinet();
        loginPage.registrationClick();
        registrationPage.goToLoginPage();
        loginPage.loginData(user.getEmail(), user.getPassword());
        loginPage.loginClick();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Соберите бургер')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
        isUserCreated = true;
    }
    @Test
    @Description("Проверка авторизации через кнопку войти страницы восстановления")
    @Step("Переходим на страницу логина")
    public void testLoginForgotPasswordPage() {
        UserApi.createUserRequest(user);
        mainPage.goToPersonalCabinet();
        loginPage.restorePasswordClick();
        forgotPasswordPage.goToLoginPage();
        loginPage.loginData(user.getEmail(), user.getPassword());
        loginPage.loginClick();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Соберите бургер')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
        isUserCreated = true;
    }

}
