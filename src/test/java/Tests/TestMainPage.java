package Tests;

import HelperClasses.MainPage;
import HelperClasses.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


import static HelperApi.URL.BASE_URL;


public class TestMainPage {
    private WebDriver driver;
    private MainPage mainPage;


    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.get(BASE_URL);
        mainPage = new MainPage(driver);

    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Проверка отображения ингредиентов")
    @Step("Проверяем отображение булок и клик по булкам")
    public void testBun() {
        mainPage.goToIngridient();
        mainPage.goToBun();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement tabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[text()='Булки']/..")));
        String currentClassName = tabElement.getAttribute("class");
        Assert.assertTrue("Класс элемента не изменился на ожидаемое значение", currentClassName.contains("tab_tab_type_current__2BEPc"));
    }
    @Test
    @Description("Проверка отображения соусов")
    @Step("Переключаем на вкладку соусов и проверяем отображение")
    public void testSouces() {
        mainPage.goToSouces();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement tabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[text()='Соусы']/..")));
        String currentClassName = tabElement.getAttribute("class");
        Assert.assertTrue("Класс элемента не изменился на ожидаемое значение", currentClassName.contains("tab_tab_type_current__2BEPc"));
    }
    @Test
    @Description("Проверка отображения начинки")
    @Step("Переключаем на вкладку начинки")
    public void testIngridient() {
        mainPage.goToIngridient();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement tabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[text()='Начинки']/..")));
        String currentClassName = tabElement.getAttribute("class");
        Assert.assertTrue("Класс элемента не изменился на ожидаемое значение", currentClassName.contains("tab_tab_type_current__2BEPc"));
    }
}
