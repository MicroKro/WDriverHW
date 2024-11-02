package src.tests.kioskMod;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KioskMod {

    private WebDriver driver;
    private final String baseUrl = System.getProperty("base.url");

    @BeforeAll
    public static void webDriverInstall() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void webDriverStart() {
        System.out.println("Запуск теста");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("kiosk");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void webDriverStop() {
        System.out.println("Тест завершен");
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void kiosk () {
        driver.get(baseUrl);
        var modal = driver.findElement(By.id("myModal"));
        Assertions.assertFalse(modal.isDisplayed());
        var element = driver.findElement(By.id("openModalBtn"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(modal));
    }
}
