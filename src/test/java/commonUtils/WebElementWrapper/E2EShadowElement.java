package commonUtils.WebElementWrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * The `E2EShadowElement` class extends the `E2EElement` class to provide support for interacting with
 * elements inside a **Shadow DOM**. The Shadow DOM is a web standard that encapsulates HTML, CSS, and
 * JavaScript within a scoped subtree, making it inaccessible to standard DOM queries. This class enables
 * interaction with Shadow DOM elements by leveraging the `E2EShadowRoot` class to locate the shadow root
 * and then find elements within it.
 *
 * Key Features:
 * 1. **Shadow DOM Support**: Enables interaction with elements inside the Shadow DOM, which is not
 *    directly accessible via standard Selenium locators.
 * 2. **Reusability**: Extends the `E2EElement` class, inheriting its methods and adding Shadow DOM-specific
 *    functionality.
 * 3. **Encapsulation**: Works with `E2EShadowRoot` to locate the shadow root and then find elements within it.
 */
public class E2EShadowElement extends E2EElement {
    E2EShadowRoot Root; // Reference to the shadow root where the element is located

    /**
     * Constructor to initialize the shadow element with a shadow root and a locator.
     *
     * @param root The shadow root where the element is located.
     * @param path The locator for the element within the shadow root.
     */
    public E2EShadowElement(E2EShadowRoot root, By path) {
        super(path); // Initialize the base E2EElement with the locator
        Root = root; // Set the shadow root
    }

    /**
     * Overrides the `FindElement` method to locate the element within the shadow root.
     * This method first locates the shadow root using the `E2EShadowRoot` instance and then
     * finds the element within it using the provided locator.
     *
     * @return The WebElement located inside the shadow root.
     */
    @Override
    public WebElement FindElement() {
        // First, locate the shadow root, then find the element within it using the locator
        return Root.FindElement().findElement(Path);
    }
}