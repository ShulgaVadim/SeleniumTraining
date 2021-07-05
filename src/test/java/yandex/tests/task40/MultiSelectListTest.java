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

import java.nio.file.WatchEvent;
import java.util.*;
import java.util.stream.Collectors;

public class MultiSelectListTest {

    private WebDriver driver = new ChromeDriver();
    private static final String URL = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";
    private ArrayList<String> actualCities = new ArrayList<>(Arrays.asList("California", "Ohio", "Texas"));

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void multiSelectTest() {
        driver.get(URL);
        WebElement selectElement = driver.findElement(By.id("multi-select"));
        Select select = new Select(selectElement);
        for (String state:actualCities) {
            select.selectByValue(state);
        }

        List<String> expectedCities = select.getAllSelectedOptions().
                stream().
                map(WebElement::getText).
                collect(Collectors.toList());

        Assertions.assertEquals(expectedCities, actualCities);
    }


    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
