package commonUtils.WebElementWrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

/**
 * The `E2EShadowRoot` class extends the `E2EElement` class to provide support for interacting with
 * **Shadow DOM roots**. The Shadow DOM is a web standard that encapsulates HTML, CSS, and JavaScript
 * within a scoped subtree, making it inaccessible to standard DOM queries. This class enables
 * interaction with Shadow DOM roots by using JavaScript to access the shadow root of an element.
 *
 * Key Features:
 * 1. **Shadow DOM Root Access**: Provides methods to locate and interact with shadow roots.
 * 2. **Nested Shadow DOM Support**: Handles nested shadow roots by chaining shadow root access.
 * 3. **JavaScript Integration**: Uses JavaScript to access shadow roots, which is necessary because
 *    Selenium does not natively support Shadow DOM traversal.
 */
public class E2EShadowRoot extends E2EElement {
    E2EShadowRoot Root; // Reference to the parent shadow root (for nested shadow DOMs)

    /**
     * Constructor to initialize the shadow root with a locator.
     *
     * @param path The locator for the element whose shadow root will be accessed.
     */
    public E2EShadowRoot(By path) {
        super(path); // Initialize the base E2EElement with the locator
    }

    /**
     * Constructor to initialize the shadow root with a locator and a parent shadow root.
     * This is used for accessing nested shadow roots.
     *
     * @param path The locator for the element whose shadow root will be accessed.
     * @param root The parent shadow root (for nested shadow DOMs).
     */
    public E2EShadowRoot(By path, E2EShadowRoot root) {
        super(path); // Initialize the base E2EElement with the locator
        Root = root; // Set the parent shadow root
    }

    /**
     * Overrides the `FindElement` method to locate the shadow root of an element.
     * This method uses JavaScript to access the shadow root, as Selenium does not natively
     * support Shadow DOM traversal. If a parent shadow root is provided, it chains the
     * shadow root access.
     *
     * @return The shadow root as a WebElement.
     */
    @Override
    public WebElement FindElement() {
        if (Root != null) {
            // If a parent shadow root exists, access the shadow root of the parent element
            return (WebElement) ((JavascriptExecutor) webdriver).executeScript(
                    "return arguments[0].shadowRoot", Root.FindElement());
        } else {
            // If no parent shadow root exists, access the shadow root of the base element
            return (WebElement) ((JavascriptExecutor) webdriver).executeScript(
                    "return arguments[0].shadowRoot", super.FindElement());
        }
    }
}