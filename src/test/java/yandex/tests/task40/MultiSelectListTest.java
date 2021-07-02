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
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class MultiSelectListTest {

    private WebDriver driver = new ChromeDriver();
    private static final String URL = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void multiSelectTest() {
        driver.get(URL);
        WebElement selectElement = driver.findElement(By.id("multi-select"));
        Select select = new Select(selectElement);
        select.selectByValue("California");
        select.selectByValue("Ohio");
        select.selectByValue("Texas");

        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        List<String> expectedCities = new ArrayList<>();
        for (WebElement selectedOption : selectedOptions) {
            expectedCities.add(selectedOption.getText());
        }
        ArrayList<String> actualCities = new ArrayList<>(Arrays.asList("California", "Ohio", "Texas"));

        Assertions.assertEquals(expectedCities, actualCities);
    }


    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
