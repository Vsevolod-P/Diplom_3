package Tests;

import HelperClasses.LoginPage;
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

import static HelperApi.URL.BASE_URL_LOGIN;

public class TestLoginPage {
    private WebDriver driver;
    private LoginPage loginPage;
    private User user;
    private UserApi UserApi;
    private boolean isUserCreated;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.get(BASE_URL_LOGIN);

        loginPage = new LoginPage(driver);
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
    @Description("Проверка авторизации")
    @Step("Вводим данные пользователя")
    public void testLogin() {
        UserApi.createUserRequest(user);
        loginPage.loginData(user.getEmail(), user.getPassword());
        loginPage.loginClick();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Соберите бургер')]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://stellarburgers.nomoreparties.site/");
        isUserCreated = true;
    }
}
