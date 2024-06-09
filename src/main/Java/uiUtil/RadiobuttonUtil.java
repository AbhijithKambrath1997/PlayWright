package uiUtil;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class RadiobuttonUtil {


    private final Page page;

    public RadiobuttonUtil(Page page) {
        this.page = page;
    }

    /**
     * This method deals with selecting radioButton
     * @param radioButtonLocator = locator for the radioButton
     */
    public void selectRadioButton(String radioButtonLocator) {
        ElementHandle radioButton = page.waitForSelector(radioButtonLocator);
        radioButton.click();
    }

    /**
     * This method deals if radio button is selected by locator
     * @param radioButtonLocator = locator for the radioButton
     */
    public boolean isRadioButtonSelected(String radioButtonLocator) {
        ElementHandle radioButton = page.waitForSelector(radioButtonLocator);
        return radioButton.isChecked();
    }

    /**
     * This method returns selected radio button value by locator
     * @param radioButtonLocator = locator for the radioButton
     */
    public String getSelectedRadioButtonValue(String radioButtonLocator) {
        ElementHandle radioButton = page.waitForSelector(radioButtonLocator);
        return radioButton.getAttribute("value");
    }

    /**
     * This method checks radio button is visible
     * @param radioButtonLocator = locator for the radioButton
     */
    public boolean isRadioButtonVisible(String radioButtonLocator) {
        ElementHandle radioButton = page.waitForSelector(radioButtonLocator);
        return radioButton.isVisible();
    }
    /**
     * This method checks radio button is enabled
     * @param radioButtonLocator = locator for the radioButton
     */
    public boolean isRadioButtonSelectable(String radioButtonLocator) {
        ElementHandle radioButton = page.waitForSelector(radioButtonLocator);
        return radioButton.isEnabled();
    }
}

