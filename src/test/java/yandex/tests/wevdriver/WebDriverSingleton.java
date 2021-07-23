package yandex.tests.wevdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static volatile ThreadLocal<WebDriver> driver = new InheritableThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            synchronized (WebDriverSingleton.class) {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get().manage().window().maximize();
            }
        }
        return driver.get();
    }

    public static void closeBrowser() {
        driver.get().quit();
        driver.set(null);
    }
}
