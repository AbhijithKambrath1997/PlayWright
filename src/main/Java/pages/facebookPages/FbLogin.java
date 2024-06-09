package pages.facebookPages;

import com.microsoft.playwright.Page;

import java.util.List;

public class FbLogin {

    private final Page page;

    private final String id = "input#email";
    private final String password = "input#pass";
    private final String loginButton = "button[name='login']";
    private final String forgotPassword = "//a[text()='Forgotten password?']";
    private final String createNewAccount = "//a[contains(text(),'Create new account')]";


    public FbLogin(Page page) {
        this.page = page;
    }

    public void setId(String value) {
        page.fill(id, value);
    }

    public void setPassword(String value) {
        page.fill(password, value);
    }

    public boolean isEnabled() {
        List<String> buttons = List.of(loginButton);
        for (String button : buttons) {
            return page.locator(button).isEnabled();
        }
        return false;
    }

    public boolean isVisible() {
        List<String> icons = List.of(createNewAccount, forgotPassword);
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
        page.click(createNewAccount);
    }

    public FbCreateAccount navigateToFbCreateAccount() {
        page.click(createNewAccount);
        return new FbCreateAccount(page);
    }

}
