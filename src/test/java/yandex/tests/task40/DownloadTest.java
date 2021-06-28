package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownloadTest {

    public WebDriver driver = new ChromeDriver();
    public WebDriverWait wait = new WebDriverWait(driver, 20);
    public String URL = "https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html";


    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void downloadTest() {
        driver.get(URL);
        driver.findElement(By.id("cricle-btn")).click();
        WebElement percentage = driver.findElement(By.className("percenttext"));
        wait.until(ExpectedConditions.textToBePresentInElement(percentage, "50%"));
        driver.navigate().refresh();
        WebElement percent = driver.findElement(By.className("percenttext"));//don't like it, but without this string test fails
        Assertions.assertEquals(percent.getText(), "0%");
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
