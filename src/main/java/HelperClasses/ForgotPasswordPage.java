package HelperClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
;
public class ForgotPasswordPage extends BasePage{
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим по кнопке Войти")
    public void goToLoginPage() {
        driver.findElement(By.xpath("//a[text()='Войти']")).click();
    }

}
