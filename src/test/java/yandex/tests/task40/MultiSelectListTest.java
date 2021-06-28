package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiSelectListTest {

    public WebDriver driver = new ChromeDriver();
    public String URL = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void multiSelectTest() {
        driver.get(URL);
        Actions actions = new Actions(driver);
        WebElement multiSelectDemo = driver.findElement(By.id("multi-select"));
        List<WebElement> options = multiSelectDemo.findElements(By.tagName("option"));
        Action multipleSelect = actions.keyDown(Keys.CONTROL)
                .click(options.get(1))
                .click(options.get(4))
                .click(options.get(6))
                .build();
        multipleSelect.perform();

        List<String> cities = new ArrayList<>();
        for (int i = 0; i <= options.size() - 1; i++) {
            if (options.get(i).isSelected()) {
                cities.add(options.get(i).getText());
            }
        }

        Assertions.assertAll(
                () -> assertEquals(options.get(1).getText(), cities.get(0)),
                () -> assertEquals(options.get(4).getText(), cities.get(1)),
                () -> assertEquals(options.get(6).getText(), cities.get(2))
        );
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
