package uiUtil;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

public class DropDownUtil{

    private final Page page;

    public DropDownUtil(Page page){
        this.page = page;
    }

    /**
     * This method deals with selecting drop down by passing the value of the option
     * @param dropdownSelector = locator for the dropdown
     * @param optionValue = value of the option which needs to be selected
     */
    public void clickDropDownByValue(String dropdownSelector, String optionValue){
        Locator locator = page.locator(dropdownSelector);
        locator.selectOption(new SelectOption().setValue(optionValue));
    }

    /**
     * This method deals with selecting drop down by passing text of option
     * @param dropdownSelector = locator for the dropdown
     * @param optionText = text of the option which needs to be selected
     */
    public void clickDropDownByText(String dropdownSelector, String optionText){
        Locator locator = page.locator(dropdownSelector);
        locator.selectOption(new SelectOption().setLabel(optionText));
    }

    /**
     * This method deals with selecting drop down by passing index of option
     * @param dropdownSelector = locator for the dropdown
     * @param optionIndex = index of the option which needs to be selected
     */
    public void clickDropDownByIndex(String dropdownSelector, int optionIndex){
        Locator locator = page.locator(dropdownSelector);
        locator.selectOption(new SelectOption().setIndex(optionIndex));
    }

    /**
     * This method returns the count of dropdown
     * @param dropdownSelector = locator for the dropdown
     */
    public int getOptionCounts(String dropdownSelector){
        Locator dropDown = page.locator(dropdownSelector);
        Locator options = dropDown.locator("option");
        return options.count();
    }

    /**
     * This method returns the values inside dropdown
     * @param dropdownSelector = locator for the dropdown
     */
    public List<String> getOptionText(String dropdownSelector){
        Locator dropDown = page.locator(dropdownSelector);
        Locator options = dropDown.locator("option");
        return options.allInnerTexts();
    }
}
