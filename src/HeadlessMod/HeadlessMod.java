package src.HeadlessMod;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessMod {

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
        options.addArguments("--headless");
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
    public void headless (){
        driver.get(baseUrl);
        var element = driver.findElement(By.id("textInput"));
        element.sendKeys("ОТУС");
        Assertions.assertEquals("ОТУС", element.getAttribute("value"));
    }
}
