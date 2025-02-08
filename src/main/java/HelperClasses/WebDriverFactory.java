package HelperClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

    public static WebDriver createDriver() {
        WebDriver driver;
        String browser = System.getProperty("browser", "chrome");
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/Users/vpetrenko/WebDriver/yandexdriver");
                System.setProperty("webdriver.chrome.binary", "/Apllications/yandex");
                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        return driver;
    }
}
