package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinetPage extends BasePage {
    public PersonalCabinetPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        driver.findElement(By.xpath("//button[text()='Выйти']")).click();
    }
}
