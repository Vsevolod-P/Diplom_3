package HelperClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @Step("Вводи данные для входа")
    public void loginData(String email, String password) {
        driver.findElement(By.xpath("(//fieldset)[1]/div/div/input")).sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

    }
    @Step("Кликаем по кнопке Войти")
    public void loginClick() {
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
    }

    @Step("Кликаем по кнопке зарегистрироваться")
    public void registrationClick() {
        driver.findElement(By.xpath("//a[text()='Зарегистрироваться']")).click();
    }
    @Step("Кликаем по кнопке Восстановить пароль")
    public void restorePasswordClick() {
        driver.findElement(By.xpath("//a[text()='Восстановить пароль']")).click();
    }


}
