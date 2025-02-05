package HelperClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage extends BasePage {
    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    public void goToBuns() {
        driver.findElement(By.linkText("Булки")).click();
    }

    public void goToSauces() {
        driver.findElement(By.linkText("Соусы")).click();
    }

    public void goToFillings() {
        driver.findElement(By.linkText("Начинки")).click();
    }
}