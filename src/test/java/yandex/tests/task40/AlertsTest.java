package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsTest {
    private WebDriver driver = new ChromeDriver();;
    private static final String URL = "https://www.seleniumeasy.com/test/javascript-alert-box-demo.html";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void openPage() {
        driver.get(URL);
    }

    @Test
    public void verifyAlertTextTest(){
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@onclick='myAlertFunction()']"));
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("I am an alert box!", alert.getText());
    }

    @Test
    public void confirmAlertConfirmBoxTest(){
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@onclick='myConfirmFunction()']"));
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assertions.assertEquals("You pressed OK!", driver.findElement(By.id("confirm-demo")).getText());
    }

    @Test
    public void dismissAlertConfirmBoxTest(){
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@onclick='myConfirmFunction()']"));
        clickMeButton.click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        Assertions.assertEquals("You pressed Cancel!", driver.findElement(By.id("confirm-demo")).getText());
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
