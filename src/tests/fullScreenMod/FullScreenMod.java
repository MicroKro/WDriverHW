package src.tests.fullScreenMod;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FullScreenMod {

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
        options.addArguments("fullscreen");
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
    public void fullScreen () {
        driver.get(baseUrl);
        var nameInputElement = driver.findElement(By.id("name"));
        nameInputElement.sendKeys("Диана");
        var emailInputElement = driver.findElement(By.id("email"));
        emailInputElement.sendKeys("myau@imbox.ru");
        var element = driver.findElement(By.cssSelector("#sampleForm button"));
        element.click();
        var messageBox = driver.findElement(By.id("messageBox"));
        Assertions.assertEquals("Форма отправлена с именем: Диана и email: myau@imbox.ru", messageBox.getText());
    }
}
