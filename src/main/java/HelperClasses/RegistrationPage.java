package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void register(String name, String email, String password) {
        driver.findElement(By.xpath("(//fieldset)[1]/div/div/input")).sendKeys(name);
        driver.findElement(By.xpath("(//fieldset)[2]/div/div/input")).sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Зарегистрироваться']")).click();

    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath("//p[@class='input__error text_type_main-default']")).getText();
    }
}
