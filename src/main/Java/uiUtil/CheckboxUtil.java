package uiUtil;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class CheckboxUtil {

    private final Page page;

    public CheckboxUtil(Page page) {
        this.page = page;
    }

    /**
     * This method deals with selecting checkBox
     * @param checkboxSelector = locator for the checkBox
     */
    public void selectCheckBox(String checkboxSelector) {
        page.check(checkboxSelector);
    }

    /**
     * This method to check if a checkbox is selected
     * @param checkboxSelector = checkbox which needs to be verified if its selected
     */
    public boolean isCheckboxSelected(String checkboxSelector) {
        return page.isChecked(checkboxSelector);
    }

    /**
     * This method returns the count of checkboxes on the page
     */
    public int getCheckboxCount() {
        return page.querySelectorAll("input[type='checkbox']").size();
    }

    /**
     * This method returns labels of all checkboxes on the page
     */
    public List<String> getAllCheckboxLabels() {
        List<String> labels = new ArrayList<>();
        List<ElementHandle> checkboxHandles = page.querySelectorAll("input[type='checkbox']");
        for (ElementHandle checkboxHandle : checkboxHandles) {
            labels.add(checkboxHandle.textContent());
        }
        return labels;
    }

    /**
     * This method returns labels of all checkboxes on the page
     * @param checkboxSelector = checkbox which needs to be cleared
     */
    public void clearCheckboxSelection(String checkboxSelector) {
        page.uncheck(checkboxSelector);
    }

    /**
     * This method is to wait until a checkbox is present and visible
     * @param checkboxSelector = checkbox which needs to be visible
     */
    public void waitForCheckbox(String checkboxSelector) {
        page.waitForSelector(checkboxSelector);
    }
}

