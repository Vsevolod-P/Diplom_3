package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinetPage extends BasePage {
    public PersonalCabinetPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        driver.findElement(By.xpath("//button[text()='Выход']")).click();
    }
    public void goToConstructor() {
        driver.findElement(By.xpath("//p[text()='Конструктор']")).click();
    }

    public void goToLogo() {
        driver.findElement(By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a")).click();
    }
}
