package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;


public class DownloadTest {

    private WebDriver driver = new ChromeDriver();
    private static final String URL = "https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html";
    private Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(20))
            .pollingEvery(Duration.ofMillis(100))
            .ignoring(WebDriverException.class);

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void downloadTest() {
        driver.get(URL);
        driver.findElement(By.id("cricle-btn")).click();
        WebElement percentage = driver.findElement(By.className("percenttext"));

// Tried to refactor this method with parsing percentage to int, but int number always equals 0. Code is below
//        int number = Integer.parseInt(percentage.getText().replace("%", ""));
//        while (number < 50) {
//            System.out.println(number);
//        }

        wait.until(ExpectedConditions.textToBePresentInElement(percentage, "50%"));
        driver.navigate().refresh();
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
