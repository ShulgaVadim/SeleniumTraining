package yandex.tests.wevdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static volatile ThreadLocal<WebDriver> driver = new InheritableThreadLocal<>();
    private static final String URL = "https://oauth-stiffler8888-d3e3c:69630821-a2e1-4932-8af3-85137a45969c@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    private WebDriverSingleton() {
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            synchronized (WebDriverSingleton.class) {
                DesiredCapabilities caps = DesiredCapabilities.edge();
                caps.setCapability("version", "latest");
                caps.setCapability("platform", "Windows 10");

//                DesiredCapabilities caps = DesiredCapabilities.firefox();
//                caps.setCapability("version", "39.0");
//                caps.setCapability("platform", "Windows 8.1");
//
//                DesiredCapabilities caps = DesiredCapabilities.chrome();
//                caps.setCapability("version", "40");
//                caps.setCapability("platform", "Linux");

                try {
                    WebDriver webDriver = new RemoteWebDriver(new URL(URL), caps);
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