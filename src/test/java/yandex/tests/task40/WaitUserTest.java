package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUserTest {

    private WebDriver driver = new ChromeDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 10);
    private static final String URL = "https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void waitUserTest() {
        driver.get(URL);
        driver.findElement(By.id("save")).click();
        WebElement imageLoader = driver.findElement(By.xpath("//div[@id = 'loading']/img[contains(@src, 'loader-image')]"));
        wait.until(ExpectedConditions.invisibilityOf(imageLoader));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id = 'loading']/img[contains(@src, 'portraits')]")));
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
