package uiUtil;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.options.MouseButton;


public class ButtonUtil {

    private final Page page;

    public ButtonUtil(Page page) {
        this.page = page;
    }

    /**
     * This method helps to Click a button by selector
     * @param buttonSelector = locator for the button
     */
    public void clickButton(String buttonSelector) {
        page.click(buttonSelector);
    }

    /**
     * This method helps to Check if a button is visible
     * @param buttonSelector = locator for the button
     */
    public boolean isButtonVisible(String buttonSelector) {
        ElementHandle button = page.querySelector(buttonSelector);
        return button != null && button.isVisible();
    }

    /**
     * This method helps to Wait for a button to be clickable with a timeout
     * @param buttonSelector = locator for the button
     */
    public void waitForButtonToBeClickable(String buttonSelector, int timeoutMilliseconds) {
        page.waitForSelector(buttonSelector, new Page.WaitForSelectorOptions().setTimeout(timeoutMilliseconds));
    }

    /**
     * This method return the text content of a button
     * @param buttonSelector = locator for the button
     */
    public String getButtonText(String buttonSelector) {
        ElementHandle button = page.querySelector(buttonSelector);
        return button != null ? button.innerText() : null;
    }

    /**
     * This method helps to Double-click a button
     * @param buttonSelector = locator for the button
     */
    public void doubleClickButton(String buttonSelector) {
        page.dblclick(buttonSelector);
    }

    /**
     * This method helps to Right-click a button
     * @param buttonSelector = locator for the button
     */
    public void rightClickButton(String buttonSelector) {
        page.click(buttonSelector, new Page.ClickOptions().setButton(MouseButton.RIGHT));
    }

    /**
     * This method helps to Hover over a button
     * @param buttonSelector = locator for the button
     */
    public void hoverOverButton(Page page, String buttonSelector) {
        page.hover(buttonSelector);
    }

    /**
     * This method helps to click keyboard keys
     * @param buttonSelector = locator for the button
     * @param key = Keyboard key
     */
    public void pressEnterOnButton(String buttonSelector, String key) {
        page.press(buttonSelector, key);
    }
}
