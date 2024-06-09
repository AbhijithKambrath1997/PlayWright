package test.gmail;

import org.testng.Assert;
import org.testng.annotations.Test;
import retry.RetryUtil;

import java.util.List;

public class GmailTest extends GmailBaseTest {

    @Test
    public void testLoginPage() {
        List<String> details = loginPage.getPageDetails();
        System.out.println("Page url is : " + details.get(0));
        System.out.println("Page title is : " + details.get(1));
        String text = loginPage.getText();
        System.out.println(text);

        Assert.assertTrue(loginPage.isVisible(), "Icons are not Visible");
        Assert.assertTrue(loginPage.isEnabled(), "Buttons are not Enabled");
        RetryUtil.retry(() -> {
            Assert.assertTrue(loginPage.isEnabled(), "Buttons are not Enabled");
            return true;
        }, 10);
        loginPage.setId(properties.getProperty("gmailUserName"));
        loginPage.click();
    }
}
