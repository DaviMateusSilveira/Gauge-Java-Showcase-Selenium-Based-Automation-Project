package commonUtils.WebElementWrapper;

import java.util.ArrayList;
import java.util.Collection;
import org.openqa.selenium.By;

public class E2EElementList {
    By Path; // Locator path for the elements
    ArrayList<E2EElement> lista = new ArrayList<E2EElement>(); // List to store E2EElement objects

    // Constructor to initialize the list with a locator
    public E2EElementList(By path) {
        Path = path;
    }

    /**
     * Finds all elements matching the locator and updates the list.
     * This method is called internally before performing any list operations.
     */
    public void findElements() {
        lista.clear(); // Clear the existing list
        // Iterate through all elements matching the locator and add them to the list
        for (int i = 0; i < new E2EElement(Path).webdriver.findElements(Path).size(); i++) {
            lista.add(new E2EElement(Path, i)); // Add each element to the list
        }
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     */
    public int size() {
        findElements(); // Update the list
        return lista.size();
    }

    /**
     * Checks if the list is empty.
     *
     * @return True if the list is empty, otherwise false.
     */
    public boolean isEmpty() {
        findElements(); // Update the list
        return lista.isEmpty();
    }

    /**
     * Checks if the list contains a specific element.
     *
     * @param o The element to check for.
     * @return True if the element is in the list, otherwise false.
     */
    public boolean contains(Object o) {
        findElements(); // Update the list
        return lista.contains(o);
    }

    /**
     * Converts the list to an array.
     *
     * @return An array containing all elements in the list.
     */
    public Object[] toArray() {
        findElements(); // Update the list
        return lista.toArray();
    }

    /**
     * Converts the list to an array of a specific type.
     *
     * @param a The array into which the elements will be stored.
     * @return An array containing all elements in the list.
     */
    public <T> T[] toArray(T[] a) {
        findElements(); // Update the list
        return lista.toArray(a);
    }

    /**
     * Checks if the list contains all elements in a specified collection.
     *
     * @param c The collection to check for.
     * @return True if all elements in the collection are in the list, otherwise false.
     */
    public boolean containsAll(Collection<?> c) {
        findElements(); // Update the list
        return lista.containsAll(c);
    }

    /**
     * Clears the list of elements.
     */
    public void clear() {
        findElements(); // Update the list
        lista.clear();
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     */
    public E2EElement get(int index) {
        findElements(); // Update the list
        return lista.get(index);
    }

    /**
     * Returns the entire list of elements.
     *
     * @return The list of E2EElement objects.
     */
    public ArrayList<E2EElement> getList() {
        findElements(); // Update the list
        return lista;
    }

    /**
     * Returns the index of the first occurrence of a specific element.
     *
     * @param o The element to search for.
     * @return The index of the element, or -1 if not found.
     */
    public int indexOf(Object o) {
        return lista.indexOf(o);
    }

    /**
     * Returns the index of the last occurrence of a specific element.
     *
     * @param o The element to search for.
     * @return The index of the element, or -1 if not found.
     */
    public int lastIndexOf(Object o) {
        return lista.lastIndexOf(o);
    }
}