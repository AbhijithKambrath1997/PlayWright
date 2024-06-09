package pages.gmailPages;

import com.microsoft.playwright.Page;

import java.util.List;

public class LoginPage {

    /**
     * Locators
     */
    private Page page;
    private final String emailId = "//input[@type='email']";
    private final String forgotButton = "//button[text()='Forgot email?']";
    private final String createAccount = "//span[contains(text(), 'Create')]";
    private final String emailText = "//a[contains(text(), 'Learn')]";
    private final String nextButton = "//span[text()='Next']";
    private final String help = "//a[text()='Help']";
    private final String privacy = "//a[text()='Privacy']";
    private final String terms = "//a[text()='Terms']";
    private final String personalAccount = "//span[contains(text(),'personal')]";

    /**
     * Constructor
     */
    public LoginPage(Page page) {
        this.page = page;
    }

    /**
     * Methods
     */
    public String getText() {
        return page.textContent(emailText);
    }

    public void setId(String value) {
        page.fill(emailId, value);
    }

    public boolean isEnabled() {
        List<String> buttons = List.of(forgotButton, nextButton);
        for (String button : buttons) {
            return page.locator(button).isEnabled();
        }
        return false;
    }

    public boolean isVisible() {
        List<String> icons = List.of(forgotButton, nextButton, help, privacy, terms);
        for (String icon : icons) {
            return page.locator(icon).isEnabled();
        }
        return false;
    }

    public List<String> getPageDetails() {
        String url = page.url();
        String title = page.title();
        return List.of(url, title);
    }

    public void click() {
        page.click(nextButton);
    }

    public CreateAccountPage navigateToCreateAccount() {
        page.click(createAccount);
        page.click(personalAccount);
        return new CreateAccountPage(page);
    }

}
