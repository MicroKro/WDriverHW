package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ConfigDriver {

    static WebDriver driver;

    @BeforeAll
    public static void webDriverInstall() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void webDriverStart() {
        System.out.println("Запуск теста");
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.get("https://otus.home.kartushin.su/training.html");

        var element = driver.findElement(By.id("textInput"));
        element.sendKeys("ОТУС");
        Assertions.assertEquals("ОТУС", element.getAttribute("value"));
    }

    @Test
    public void kiosk () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("kiosk");
        driver = new ChromeDriver(options);
        driver.get("https://otus.home.kartushin.su/training.html");

        var modal = driver.findElement(By.id("myModal"));
        Assertions.assertFalse(modal.isDisplayed());

        var element = driver.findElement(By.id("openModalBtn"));
        element.click();

        Assertions.assertTrue(modal.isDisplayed());
    }

    @Test
    public void fullScreen () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("fullscreen");
        driver = new ChromeDriver(options);
        driver.get("https://otus.home.kartushin.su/training.html");

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
