package utils;

import io.qameta.allure.Attachment;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import yandex.tests.wevdriver.WebDriverSingleton;

import java.util.Optional;


@Log4j2
public class MyTestWatcher implements TestWatcher {

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        log.info("Test Aborted for test {}: ", extensionContext.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        log.info("Test Disabled for test {}: with reason :- {}",
                extensionContext.getDisplayName(),
                optional.orElse("No reason"));
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable e) {
        log.info("Test Failed for test {}: with reason :- {}",
                extensionContext.getDisplayName(), extensionContext.getExecutionException());
        takeScreenshot(extensionContext.getDisplayName());
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        log.info("Test Successful for test {}: ", extensionContext.getDisplayName());
    }

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] takeScreenshot(String name) {
        return ((TakesScreenshot) WebDriverSingleton.getInstance()).getScreenshotAs(OutputType.BYTES);
    }
}