package yandex.tests.wevdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static volatile ThreadLocal<WebDriver> driver = new InheritableThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            synchronized (WebDriverSingleton.class) {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setPlatform(Platform.WINDOWS);
                try {
                    WebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                    driver.set(webDriver);
                    driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    driver.get().manage().window().maximize();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        }
        return driver.get();
    }

    public static void closeBrowser() {
        driver.get().quit();
        driver.set(null);
    }
}
