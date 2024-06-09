package pages.facebookPages;

import com.microsoft.playwright.Page;

public class FbCreateAccount {

    private final Page page;

    private final String firstName = "input[placeholder='First name']";
    private final String surname = "input[name='lastname']";
    private final String newId = "input[name='reg_email__'] ";
    private final String newPassword = "input[data-type='password']";

    public FbCreateAccount(Page page){
        this.page = page;
    }

    public void setNameFields(String firstname, String lastName){
        page.fill(firstName, firstname);
        page.fill(surname, lastName);
    }

    public void setIdAndPassWord(String id, String password) {
        page.fill(newId, id);
        page.fill(newPassword, password);
    }
}
