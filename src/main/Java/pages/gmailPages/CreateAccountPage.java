package pages.gmailPages;

import com.microsoft.playwright.Page;

import java.util.List;

public class CreateAccountPage {

    private final Page page;

    private final String firstName ="//input[@id = 'firstName']";
    private final String lastName = "//input[@id = 'lastName']";
    private final String accountText = "//span[contains(text(),'Account')]";
    private final String descriptionText = "//span[contains(text(),'Enter')]";
    private final String nextTab = "//span[contains(text(),'Next')]";

    public CreateAccountPage(Page page) {
        this.page = page;
    }

    public void fillName(String name, String initial){
        page.fill(firstName, name);
        page.fill(lastName, initial);
    }

    public List<String> nameDetails(){
        return List.of(page.inputValue(firstName), page.inputValue(lastName));
    }

    public String verifyTextField(){
        return page.innerText(accountText);
    }

    public String verifyDescriptionField(){
        return page.innerText(descriptionText);
    }

    public void navigateToDetailsPage(){
        page.click(nextTab);
    }

}
