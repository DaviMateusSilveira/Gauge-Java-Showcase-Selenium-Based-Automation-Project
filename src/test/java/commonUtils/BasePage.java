package commonUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final int DEFAULT_TIMEOUT = 20;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for element to be visible
     */
    protected WebElement waitForElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible within " + DEFAULT_TIMEOUT + " seconds: " + locator);
        }
    }

    /**
     * Wait for element to be visible (WebElement version)
     */
    protected WebElement waitForElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible within " + DEFAULT_TIMEOUT + " seconds: " + element);
        }
    }

    /**
     * Wait for element to be clickable
     */
    protected WebElement waitForElementClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not clickable within " + DEFAULT_TIMEOUT + " seconds: " + locator);
        }
    }

    /**
     * Wait for element to be clickable (WebElement version)
     */
    protected WebElement waitForElementClickable(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not clickable within " + DEFAULT_TIMEOUT + " seconds: " + element);
        }
    }

    /**
     * Safe click with wait
     */
    protected void safeClick(By locator) {
        waitForElementClickable(locator).click();
    }

    /**
     * Safe click with wait (WebElement version)
     */
    protected void safeClick(WebElement element) {
        waitForElementClickable(element).click();
    }

    /**
     * Safe send keys with wait
     */
    protected void safeSendKeys(By locator, String text) {
        WebElement element = waitForElementDisplayed(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Safe send keys with wait (WebElement version)
     */
    protected void safeSendKeys(WebElement element, String text) {
        waitForElementDisplayed(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Check if element exists
     */
    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Wait for element to disappear
     */
    protected boolean waitForElementToDisappear(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Scroll element into view
     */
    protected void scrollIntoView(By locator) {
        WebElement element = waitForElementDisplayed(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scroll element into view (WebElement version)
     */
    protected void scrollIntoView(WebElement element) {
        waitForElementDisplayed(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Get text with wait
     */
    protected String getTextFromElement(By locator) {
        return waitForElementDisplayed(locator).getText();
    }

    /**
     * Get text with wait (WebElement version)
     */
    protected String getTextFromElement(WebElement element) {
        return waitForElementDisplayed(element).getText();
    }

    /**
     * Check if element is displayed
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Check if element is displayed (WebElement version)
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Wait for page load complete
     */
    protected void waitForPageLoadComplete() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Switch to frame
     */
    protected void switchToFrame(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    /**
     * Switch back to default content
     */
    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * Handle alert
     */
    protected void handleAlert(boolean accept) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    /**
     * Custom wait with condition
     */
    protected void waitForCustomCondition(By locator, String attribute, String expectedValue) {
        wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            return element.getAttribute(attribute).equals(expectedValue);
        });
    }
}