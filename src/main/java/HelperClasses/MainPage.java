package HelperClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
   private String personalCabinetButtonLink = ".//p[(@class='AppHeader_header__linkText__3q_va ml-2') and (text()='Личный Кабинет')]";
   private String loginButtonLink = "//button[text()='Войти в аккаунт']";


    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("переходим по кнопке личного кабинета")
    public void goToPersonalCabinet() {
        driver.findElement(By.xpath(personalCabinetButtonLink)).click();
    }
    @Step("переходим по кнопке войти в аккаунт")
    public void goToLoginAcc() {
        driver.findElement(By.xpath(loginButtonLink)).click();
    }

    @Step("переходим на булки")
    public void goToBun() {
        driver.findElement(By.xpath("//span[text()='Булки']/..")).click();
    }
    @Step("переходим на соусы")
    public void goToSouces() {
        driver.findElement(By.xpath("//span[text()='Соусы']/..")).click();
    }
    @Step("переходим на начинки")
    public void goToIngridient() {
        driver.findElement(By.xpath("//span[text()='Начинки']/..")).click();
    }

}