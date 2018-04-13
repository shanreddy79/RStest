package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

/**
 * "TestDataPrep.java" class file handles all the supportive methods for automation framework
 *
 * @author shan.reddy
 */
public class TestDataPrep {

    public WebDriver driver;

    /**
     * will fluently wait till the expected Element Locator is visible in the page
     *
     * @param driver
     * @param locator :  expected locator place to verify the expected result
     */
    public WebElement waitForElementLocator(WebDriver driver, WebElement locator) throws Exception {
        int timeout = 5;
        int attempts = 1;
        WebElement element = null;
        boolean clickable = false;
        Exception e = null;
        while (!clickable) {
            try {
                FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                        .withTimeout(timeout, TimeUnit.SECONDS)
                        .pollingEvery(1000, TimeUnit.MILLISECONDS)
                        .ignoring(org.openqa.selenium.NoSuchElementException.class);
                element = fluentWait.until(ExpectedConditions.visibilityOf(locator));
                element = fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
                clickable = true;
            } catch (Exception e1) {
                attempts++;
                e = e1;
                if (attempts == 5) {
                    break;
                }
            }
        }
        if (!clickable) {
            throw new Exception("Eventually timed out within click() method", e);
        }
        return element;
    }

    /**
     * will fluently wait till the expected Element Locator is visible in the page
     *
     * @param driver
     * @param locator :  expected locator place to verify the expected result
     */
    public WebElement waitForElement(WebDriver driver, By locator) throws Exception {
        int timeout = 5;
        int attempts = 1;
        WebElement element = null;
        boolean clickable = false;
        Exception e = null;
        while (!clickable) {
            try {
                FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                        .withTimeout(timeout, TimeUnit.SECONDS)
                        .pollingEvery(1000, TimeUnit.MILLISECONDS)
                        .ignoring(org.openqa.selenium.NoSuchElementException.class);
                element = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                element = fluentWait.until(ExpectedConditions.elementToBeClickable(locator));

                clickable = true;
            } catch (Exception e1) {
                attempts++;
                e = e1;
                if (attempts == 8) {
                    break;
                }
            }
        }
        if (!clickable) {
            throw new Exception("Eventually timed out within click() method", e);
        }
        return element;
    }
}

