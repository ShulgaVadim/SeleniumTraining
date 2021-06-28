package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsTest {
    public WebDriver driver = new ChromeDriver();;
    public String URL = "https://www.seleniumeasy.com/test/javascript-alert-box-demo.html";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void confirmAlertBox(){
        driver.get(URL);
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@onclick='myAlertFunction()']"));
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Assertions.assertEquals("I am an alert box!", alertText);
    }

    @Test
    public void confirmAlertConfirmBox(){
        driver.get(URL);
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@onclick='myConfirmFunction()']"));
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        WebElement confirmMessage = driver.findElement(By.id("confirm-demo"));
        Assertions.assertEquals("You pressed OK!", confirmMessage.getText());
    }

    @Test
    public void dismissAlertConfirmBox(){
        driver.get(URL);
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@onclick='myConfirmFunction()']"));
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        WebElement confirmMessage = driver.findElement(By.id("confirm-demo"));

        Assertions.assertEquals("You pressed Cancel!", confirmMessage.getText());
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
