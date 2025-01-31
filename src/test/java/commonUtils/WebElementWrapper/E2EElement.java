package commonUtils.WebElementWrapper;

import java.util.ArrayList;
import commonUtils.DriverFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class E2EElement {
    public By Path; // Locator path for the element
    WebDriver webdriver = DriverFactory.getDriver(); // WebDriver instance

    private int Index = 0; // Index for locating elements in a list
    private E2EElementList comboboxOptionsList = new E2EElementList(Path); // List of combo box options

    // Constructor to initialize the element with a locator
    public E2EElement(By path) {
        Path = path;
    }

    // Constructor to initialize the element with a locator and an index
    public E2EElement(By path, int index) {
        Path = path;
        Index = index;
    }

    // Returns the number of HTTP connections (for debugging or monitoring)
    Object numberOfConnections() {
        return ((JavascriptExecutor) webdriver).executeScript("return window.openHTTPs");
    }

    // Returns the text content of the element
    public String text() {
        return FindElement().getText();
    }

    // Returns the value attribute of the element
    public String value() {
        return FindElement().getAttribute("value");
    }

    // Checks if the element is displayed on the page
    public Boolean isDisplayed() {
        return FindElement().isDisplayed();
    }

    // Checks if the element is selected (e.g., checkboxes, radio buttons)
    public Boolean isSelected() {
        return FindElement().isSelected();
    }

    // Performs a default click action on the element
    public void click() {
        FindElement().click();
    }

    // Retries clicking the element up to 10 times (useful for flaky elements)
    public void persistentclick() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            try {
                click();
                break; // Exit loop if click is successful
            } catch (Exception e) {
                Thread.sleep(500); // Wait before retrying
            }
        }
    }

    // Performs a right-click (context click) on the element
    public void rightClick() {
        new Actions(webdriver).contextClick(FindElement()).perform();
    }

    // Sends keys to the element after focusing on it, optionally clearing existing text
    public void sendKeysFocused(String keys, Boolean clear) {
        Actions act = new Actions(webdriver);
        if (clear) {
            act.click(FindElement())
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a") // Select all text
                    .keyUp(Keys.CONTROL)
                    .sendKeys(Keys.BACK_SPACE) // Clear text
                    .sendKeys(keys) // Enter new text
                    .perform();
        } else {
            act.click(FindElement())
                    .sendKeys(keys) // Enter text without clearing
                    .perform();
        }
    }

    // Selects a value from a combo box by matching the displayed text
    public void selectValue(By selector, String value, String nomeCombo) {
        click(); // Open the combo box
        E2EElementList comboboxOptionsList = new E2EElementList(selector);
        ArrayList<E2EElement> lista = comboboxOptionsList.getList();
        System.out.println("------------------------------------------------");
        System.out.println("Lendo Combo de " + nomeCombo + " com " + lista.size() + " elementos:");
        for (int i = 0; i < lista.size(); i++) {
            E2EElement x = lista.get(i);
            System.out.println((i + 1) + "ª Opção encontrada: " + x.text() + ", Opção procurada: " + value);
            if (x.text().contains(value)) {
                x.click(); // Click the matching option
                break;
            }
        }
    }

    // Performs a double-click action on the element
    public void doubleclick() {
        new Actions(webdriver).doubleClick(FindElement()).perform();
    }

    // Clears the text content of the element
    public void clear() {
        FindElement().clear();
    }

    // Sends keys to the element after clearing its content
    public void sendKeys(String keys) {
        if (StringUtils.isEmpty(keys)) return; // Skip if keys are empty
        FindElement().clear();
        FindElement().sendKeys(keys);
    }

    // Returns the value of a specified attribute of the element
    public String getAttribute(String attribute) {
        return FindElement().getAttribute(attribute);
    }

    // Returns the value of a specified DOM property of the element
    public String getDomProperty(String property) {
        return FindElement().getDomProperty(property);
    }

    // Finds and returns the WebElement based on the locator and index
    public WebElement FindElement() {
        if (Index == 0) {
            return webdriver.findElement(Path); // Return the first matching element
        }
        return webdriver.findElements(Path).get(Index); // Return the element at the specified index
    }

    // Scrolls the element into view with smooth behavior
    public void scrollIntoView() {
        ((JavascriptExecutor) webdriver).executeScript(
                "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", FindElement());
    }

    // Scrolls the element vertically by a specified value
    public void scrollTop(int value) {
        ((JavascriptExecutor) webdriver).executeScript(
                "arguments[0].scrollTop += " + value + ";", FindElement());
    }

    // Sets the vertical scroll position of the element to a specified value
    public void scrollTop2(int value) {
        ((JavascriptExecutor) webdriver).executeScript(
                "arguments[0].scrollTop = arguments[1];", FindElement(), value);
    }

    // Scrolls the element horizontally by a specified value
    public void scrollLeft(int value) {
        ((JavascriptExecutor) webdriver).executeScript(
                "arguments[0].scrollLeft += " + value + ";", FindElement());
    }

    // Performs a click using JavaScript (useful for elements not interactable via Selenium)
    public void machineclick() {
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].click();", FindElement());
    }

    // Hovers over the element
    public void hover() {
        new Actions(webdriver).moveToElement(FindElement()).perform();
    }

    // Selects a value from a combo box by matching the exact text
    public void selectValue(String texto) {
        click(); // Open the combo box
        for (E2EElement element : comboboxOptionsList.getList()) {
            System.out.println(element.text());
            if (element.text().equals(texto)) {
                element.click(); // Click the matching option
                break;
            }
        }
        click(); // Close the combo box
    }

    // Checks if the element is enabled
    public Boolean isEnabled() {
        try {
            return FindElement().isEnabled();
        } catch (Exception e) {
            return false; // Return false if the element is not interactable
        }
    }

    // Checks if the element is disabled
    public Boolean isDisabled() {
        try {
            return !FindElement().isEnabled();
        } catch (Exception e) {
            return true; // Return true if the element is not interactable
        }
    }
}