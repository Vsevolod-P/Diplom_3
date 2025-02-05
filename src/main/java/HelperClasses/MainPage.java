package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
   private String personalCabinetButtonLink = ".//p[(@class=\"AppHeader_header__linkText__3q_va ml-2\") and (text()=\"Личный Кабинет\")]";
   private String regButtonLink = ".//a[(@class=\"Auth_link__1fOlj\") and (text()=\"Зарегистрироваться\")]";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void goToRegistration() {
        driver.findElement(By.xpath(personalCabinetButtonLink)).click();
        driver.findElement(By.xpath(regButtonLink)).click();
    }

    public void goToLogin() {
        driver.findElement(By.linkText("Войти в аккаунт")).click();
    }

    public void goToPersonalCabinet() {
        driver.findElement(By.linkText("Личный кабинет")).click();
    }
}